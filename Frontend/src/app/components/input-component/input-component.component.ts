import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
	selector: 'app-input-component',
	templateUrl: './input-component.component.html',
	styleUrls: ['./input-component.component.css'],
})
export class InputComponentComponent {
	@Input() labelText: string = '';
	@Input() placeholderText: string = '';
	@Input() controlName: string = '';
}
