import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/authservice.service';
import { RegService } from 'src/app/service/registration/reg.service';

@Component({
	selector: 'app-signup',
	templateUrl: './signup.component.html',
	styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
	userForm: FormGroup = new FormGroup({});

	constructor(
		private auth: AuthService,
		private router: Router,
		private registerService: RegService,
		private formBuilder: FormBuilder
	) {}

	ngOnInit() {
		this.userForm = this.formBuilder.group({
			name: ['', Validators.required],
			email: ['', [Validators.required, Validators.email]],
			password: ['', Validators.required],
			role: ['Magazziniere'],
		});
	}

	back() {
		this.router.navigate(['../']);
	}

	register() {
		if (this.userForm.valid) {
			const val = {
				...this.userForm.value,
				role: this.userForm.value.role.toLowerCase(),
			};

			console.log(val);

			this.registerService.register(val);
		} else {
			this.userForm.markAllAsTouched();
		}
	}
}
