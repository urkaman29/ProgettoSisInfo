import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth/authservice.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
	title = 'LabSi';
	username = '';
	isAuthenticated = false;

	constructor(private authService: AuthService) {}

	ngOnInit(): void {
		this.isAuthenticated = this.authService.isAuthenticated();

		this.authService.authState$.subscribe(({ isAuthenticated, username }) => {
			this.isAuthenticated = isAuthenticated;
			this.username = username;
		});
	}

	onLogout() {
		this.authService.logout();
	}

	isAdmin(role = 'titolare') {
		return this.authService.userHasRole(role);
	}
}
