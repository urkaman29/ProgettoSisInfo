import { Day } from './day';
import { Calendar } from './calendar';

export interface Event {
	id?: number;
	eventType: CalendarEventType;
	day?: Day;
	calendar?: Calendar;
}

export class CalendarEvent implements Event {
	constructor(
		public eventType: CalendarEventType,
		public id?: number,
		public day?: Day
	) {}
}

export type CalendarEventType =
	| CalendarEntry.work
	| CalendarEntry.holiday
	| CalendarEntry.offday
	| CalendarEntry.vacation
	| CalendarEntry.sickLeave;

export enum CalendarEntry {
	work = 'Lavoro',
	holiday = 'Feste',
	offday = 'Permesso',
	vacation = 'Ferie',
	sickLeave = 'Malattia',
}
