import { Injectable } from '@angular/core';
import { WebRequestService } from '../requests/webRequest.service';
import { Calendar } from '../../models/calendar';
import { HttpClient } from '@angular/common/http';

@Injectable({
	providedIn: 'root',
})
export class CalendarService {
	constructor(private webReqService: WebRequestService, private http: HttpClient) {}

	public getEmployees() {
		return this.webReqService.get('employees');
	}

	getCalendar() {
		return this.webReqService.get(`calendar`);
	}

	createCalendar(calendar: Calendar) {
		return this.webReqService.post('calendar', calendar).subscribe((response: any) => {
			console.log(response); // Stampa la risposta nel log per scopi di debug
			alert('Orario creato con successo!');
		});
	}

	updateCalendar(id: number, calendar: Calendar) {
		return this.webReqService
			.patch(`calendar/${id}`, calendar)
			.subscribe((response: any) => {
				console.log(response); // Stampa la risposta nel log per scopi di debug
				alert('Orario aggiornato con successo!');
			});
	}

	getCalendarByEmployeeId(selectedEmployeeId: string) {
		return this.webReqService.get(`/calendar/${selectedEmployeeId}`);
	}
}
