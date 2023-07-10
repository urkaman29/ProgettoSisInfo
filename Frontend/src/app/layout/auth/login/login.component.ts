import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/authservice.service';

@Component({
	selector: 'app-loginpage',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css'],
})
export class LoginpageComponent implements OnInit {
	register() {
		throw new Error('Method not implemented.');
	}

	hide = true;
	//Variabili login
	username: any;
	password: any;

	constructor(private auth: AuthService, private router: Router) {}

	ngOnInit(): void {
		if (this.auth.isAuthenticated()) {
			this.router.navigate(['']);
		} else {
			this.router.navigate(['']);
		}
	}

	login(): void {
		this.auth.login(this.username, this.password);
	}
}
