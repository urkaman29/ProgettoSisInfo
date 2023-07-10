import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/AuthInterceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrariTitComponent } from './layout/orari/titolari/orari-tit.component';
import { CommonModule } from '@angular/common';
import { OrariDipComponent } from './layout/orari/dipendenti/orari-dip.component';
import { LoginpageComponent } from './layout/auth/login/login.component';
import { CapitalisePipe } from './pipes/capitalise.pipe';
import { SignupComponent } from './layout/auth/signup/signup.component';
import { InputComponentComponent } from './components/input-component/input-component.component';
import { SelectComponentComponent } from './components/select-component/select-component.component';
import { ListComponent } from './layout/employee/list/list.component';



@NgModule({
	declarations: [
		AppComponent,
		LoginpageComponent,
		OrariTitComponent,
		OrariDipComponent,
		SignupComponent,
		CapitalisePipe,
		InputComponentComponent,
		SelectComponentComponent,
		ListComponent
	],
	imports: [
		HttpClientModule,
		BrowserModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		MatButtonModule,
		FormsModule,
		CommonModule,
		ReactiveFormsModule,
		
	],
	providers: [
		{
			provide: HTTP_INTERCEPTORS,
			useClass: AuthInterceptor,
			multi: true,
		},
	],
	bootstrap: [AppComponent],
})
export class AppModule {}
