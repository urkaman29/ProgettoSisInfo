import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
	selector: 'app-select-component',
	templateUrl: './select-component.component.html',
	styleUrls: ['./select-component.component.css'],
})
export class SelectComponentComponent {
	@Input() labelText: string = '';
	@Input() formControl: FormControl = new FormControl();
}
