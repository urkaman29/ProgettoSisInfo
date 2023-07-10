import { Calendar } from './calendar';
import { CalendarEvent, CalendarEventType, Event } from './event';

export interface Day {
	id?: number;
	calendar?: Calendar;
	date: string;
	mattina?: Event;
	pomeriggio?: Event;
}

export class CalendarDay implements Day {
	mattina: Event;
	pomeriggio: Event;

	constructor(
		public date: string,
		morning: CalendarEventType,
		afternoon: CalendarEventType,
		public id?: number
	) {
		this.date = date;
		this.mattina = new CalendarEvent(morning);
		this.pomeriggio = new CalendarEvent(afternoon);
	}
}

export enum WorkDays {
	monday = 'Lunedì',
	tuesday = 'Martedì',
	wednesday = 'Mercoledì',
	thursday = 'Giovedì',
	friday = 'Venerdì',
	saturday = 'Sabato',
}
