import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
	providedIn: 'root',
})
export class WebRequestService {
	readonly ROOT_URL;
	headers: HttpHeaders = new HttpHeaders({
		'Content-Type': 'application/json',
	});

	constructor(private http: HttpClient) {
		this.ROOT_URL = 'http://localhost:8081';
	}

	get(uri: string) {
		return this.http.get(`${this.ROOT_URL}/${uri}`, { headers: this.headers });
	}

	post(uri: string, payload: any) {
		return this.http.post(`${this.ROOT_URL}/${uri}`, payload, { headers: this.headers });
	}

	patch(uri: string, payload: Object) {
		return this.http.patch(`${this.ROOT_URL}/${uri}`, payload);
	}

	delete(uri: string) {
		return this.http.delete(`${this.ROOT_URL}/${uri}`);
	}
}
