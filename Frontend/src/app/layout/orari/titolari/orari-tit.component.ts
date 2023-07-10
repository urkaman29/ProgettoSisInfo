import { Component, OnInit } from '@angular/core';
import { CalendarService } from '../../../service/calendar/calendar.service';
import { User } from 'src/app/models/user';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CalendarEntry } from 'src/app/models/event';
import { CalendarDay, WorkDays } from 'src/app/models/day';
import { CalendarWeek } from 'src/app/models/calendar';

@Component({
	selector: 'app-orari-tit',
	templateUrl: './orari-tit.component.html',
	styleUrls: ['./orari-tit.component.css'],
})
export class OrariTitComponent implements OnInit {
	selectedEmployeeId: number = 0;
	employees: User[] = [];

	weekForm: FormGroup = new FormGroup({});
	// generate an array wit the working days:
	days = [
		WorkDays.monday,
		WorkDays.tuesday,
		WorkDays.wednesday,
		WorkDays.thursday,
		WorkDays.friday,
		WorkDays.saturday,
	];

	activities = [
		CalendarEntry.work,
		CalendarEntry.holiday,
		CalendarEntry.offday,
		CalendarEntry.vacation,
		CalendarEntry.sickLeave,
	];

	constructor(private calendarService: CalendarService, private fb: FormBuilder) {}

	ngOnInit(): void {
		const weekStart = this.getWeekStart();
		const weekEndDate = this.getWeekEnd();

		this.calendarService
			.getEmployees()
			.subscribe((empl) => (this.employees = empl as User[]));

		this.weekForm = this.fb.group({
			start: this.formatDay(weekStart, 0),
			end: this.formatDay(weekEndDate, 0),
			days: this.fb.array(
				this.days.map((day, index) => {
					const date = this.formatDay(weekStart, index);
					return this.createDay(day, date);
				})
			),
		});
	}

	createDay(day: string, date: Date): FormGroup {
		return this.fb.group({
			name: [day],
			date: [date],
			morningActivity: [this.activities[0], Validators.required],
			afternoonActivity: [this.activities[0], Validators.required],
			lunch: ['12:00 -14:00'],
		});
	}

	getDayForm(): FormArray {
		return this.weekForm.get('days') as FormArray;
	}

	getWeekStartOffset(): number {
		const today = new Date();
		const dayOfWeek = today.getDay();

		return today.getDate() - dayOfWeek + (dayOfWeek == 0 ? -6 : 1);
	}

	getWeekStart() {
		const today = new Date();
		return new Date(today.setDate(this.getWeekStartOffset()));
	}

	getWeekEnd() {
		const today = new Date();
		return new Date(today.setDate(this.getWeekStartOffset() + 6));
	}

	formatDay(day: Date, index: number) {
		return new Date(day.getTime() + index * 24 * 60 * 60 * 1000);
	}

	saveForm() {
		const formData = this.weekForm.value;

		const days = formData.days.map(
			(day: any) =>
				new CalendarDay(
					day.date.toISOString(),
					day.morningActivity,
					day.afternoonActivity
				)
		);

		const calendar = new CalendarWeek(
			formData.start.toISOString(),
			formData.end.toISOString(),
			days,
			this.selectedEmployeeId
		);

		this.calendarService.createCalendar(calendar);
	}
}
