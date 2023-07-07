import { Injectable } from '@angular/core';
import {WebRequestService} from "./web-request.service";

@Injectable({
    providedIn: 'root'
})
export class CalendarService {
    constructor(private webReqService: WebRequestService) {}

    createCalendar(title: string){
        return this.webReqService.post('/calendar/create', {title});
    }


    getCalendarByEmployeeId(selectedEmployeeId: string) {
        return this.webReqService.get(`/calendar/employee/${selectedEmployeeId}`);
    }
}
