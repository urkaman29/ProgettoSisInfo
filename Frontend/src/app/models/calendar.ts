import { Day } from './day';

export interface Calendar {
	weekStartDate: string;
	weekEndDate: string;
	days: Day[];
	employee: number;
}

export class CalendarWeek implements Calendar {
	constructor(
		public weekStartDate: string,
		public weekEndDate: string,
		public days: Day[],
		public employee: number
	) {}
}
