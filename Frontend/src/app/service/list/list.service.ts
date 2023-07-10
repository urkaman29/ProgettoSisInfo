import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/models/user';
import { WebRequestService } from '../requests/webRequest.service';

@Injectable({
	providedIn: 'root',
})
export class ListService {
	constructor(private webReqService: WebRequestService, private http: HttpClient) {}

	public getEmployees() {
		return this.webReqService.get('employees');
	}



}