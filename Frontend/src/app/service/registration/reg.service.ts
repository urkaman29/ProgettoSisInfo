import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';

@Injectable({
	providedIn: 'root',
})
export class RegService {
	private url = 'http://localhost:8081/register';

	constructor(private http: HttpClient) {}

	register(newUser: User) {
		return this.http.post(this.url, newUser).subscribe(
			(response: any) => {
				console.log(response); // Stampa la risposta nel log per scopi di debug
				alert('Registrazione avvenuta con successo!');
			},
			(error: any) => {
				console.error(error); // Stampa l'errore nel log per scopi di debug
				alert(`Registrazione dell'utente: ${newUser.name} fallita!`);
			}
		);
	}
}
