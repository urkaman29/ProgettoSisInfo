import { Component, OnInit } from '@angular/core';
import { CalendarService } from 'src/app/service/calendar/calendar.service';

@Component({
	selector: 'app-orari-dip',
	templateUrl: './orari-dip.component.html',
	styleUrls: ['./orari-dip.component.css'],
})
export class OrariDipComponent implements OnInit {
	selectedEmployeeId: string = '';
	selectedCalendar: any = null;
	calendarDays: any[] = [];

	constructor(private calendarService: CalendarService) {}

	ngOnInit() {}

	onChangeSelectedEmployee() {
		this.calendarService.getCalendarByEmployeeId(this.selectedEmployeeId).subscribe(
			(calendar) => {
				this.selectedCalendar = calendar;
				this.initializeCalendar();
			},
			(error) => {
				console.error('Errore durante il recupero del calendario:', error);
			}
		);
	}

	initializeCalendar() {
		if (this.selectedCalendar) {
			this.calendarDays = this.selectedCalendar.days.map((day: any) => {
				return {
					date: day.date,
					slots: [
						{ time: 'Mattina', type: day.mattina ? day.mattina.eventType : 'Lavoro' },
						{
							time: 'Pomeriggio',
							type: day.pomeriggio ? day.pomeriggio.eventType : 'Lavoro',
						},
					],
				};
			});
		} else {
			this.calendarService.getCalendarByEmployeeId(this.selectedEmployeeId).subscribe(
				(calendar: any) => {
					if (calendar) {
						this.calendarDays = calendar.days.map((day: any) => {
							return {
								date: day.date,
								slots: [
									{
										time: 'Mattina',
										type: day.mattina ? day.mattina.eventType : 'Lavoro',
									},
									{
										time: 'Pomeriggio',
										type: day.pomeriggio ? day.pomeriggio.eventType : 'Lavoro',
									},
								],
							};
						});
					} else {
						const days = this.getDaysArray(new Date(), 6);
						this.calendarDays = days.map((date, index) => {
							return {
								date: date.toLocaleDateString('it-IT', {
									weekday: 'long',
									day: 'numeric',
								}),
								slots: [
									{ time: 'Mattina', type: 'Lavoro' },
									{ time: 'Pomeriggio', type: 'Lavoro' },
								],
							};
						});
					}
				},
				(error) => {
					console.error('Errore durante il recupero del calendario:', error);
				}
			);
		}
	}

	getDaysArray(startDate: Date, numberOfDays: number) {
		const days = [];
		for (let i = 0; i < numberOfDays; i++) {
			const date = new Date(startDate);
			date.setDate(startDate.getDate() + i);
			days.push(date);
		}
		return days;
	}

	sendEmailToOwner() {
		const emailSubject = 'Richiesta permesso';
		const emailContent = '';
		const ownerEmail = 'lorenzociccone48gmail.com';

		const emailUrl = `mailto:${ownerEmail}?subject=${encodeURIComponent(
			emailSubject
		)}&body=${encodeURIComponent(emailContent)}`;

		window.open(emailUrl);
	}
}
