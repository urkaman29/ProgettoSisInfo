import { Calendar } from './calendar';

export interface User {
	id: number;
	name: string;
	password: string;
	email: string;
	role: string;
	firstName?: string;
	lastName?: string;
	calendar?: Calendar;
}

export class Employee implements User {
	constructor(
		public id: number,
		public name: string,
		public password: string,
		public email: string,
		public role: string,
		public firstName?: string,
		public lastName?: string,
		public calendar?: Calendar
	) {}
}
