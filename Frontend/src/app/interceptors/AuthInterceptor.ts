import {
	HttpEvent,
	HttpHandler,
	HttpInterceptor,
	HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../service/auth/authservice.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
	private jwtDecoder = new JwtHelperService();

	constructor(private authService: AuthService) {}

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		return this.authService.isAuthenticated()
			? this.handleAuthenticatedRequest(request, next)
			: next.handle(request);
	}

	private handleAuthenticatedRequest(
		request: HttpRequest<any>,
		next: HttpHandler
	): Observable<HttpEvent<any>> {
		return this.isTokenAboutToExpire()
			? this.handleTokenRefresh(request, next)
			: this.handleAuthorizedRequest(request, next);
	}

	private handleAuthorizedRequest(
		request: HttpRequest<any>,
		next: HttpHandler
	): Observable<HttpEvent<any>> {
		const authorizedRequest = this.addAuthorizationHeader(request);
		return next.handle(authorizedRequest);
	}

	private handleTokenRefresh(
		request: HttpRequest<any>,
		next: HttpHandler
	): Observable<HttpEvent<any>> {
		return this.authService
			.updateToken()
			.pipe(switchMap(() => this.handleAuthorizedRequest(request, next)));
	}

	private addAuthorizationHeader(request: HttpRequest<any>): HttpRequest<any> {
		return request.clone({
			setHeaders: { Authorization: `Bearer ${localStorage.getItem('TOKEN')}` },
		});
	}

	private isTokenAboutToExpire(): boolean {
		const token = localStorage.getItem('TOKEN');
		if (!token) return false;

		const decodedToken = this.jwtDecoder.decodeToken(token);
		const currentTimeInSeconds = Math.floor(Date.now() / 1000);
		return decodedToken.exp < currentTimeInSeconds + 10;
	}
}
