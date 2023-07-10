import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrariTitComponent } from './layout/orari/titolari/orari-tit.component';
import { OrariDipComponent } from './layout/orari/dipendenti/orari-dip.component';
import { LoginpageComponent } from './layout/auth/login/login.component';
import { userGuard } from './guards/user.guard';
import { adminGuard } from './guards/admin.guard';
import { SignupComponent } from './layout/auth/signup/signup.component';
import {ListComponent} from './layout/employee/list/list.component'

const routes: Routes = [
	{
		path: 'orari',
		children: [
			{ path: 'dipendente', canActivate: [userGuard], component: OrariDipComponent },
			{ path: 'titolare', canActivate: [adminGuard], component: OrariTitComponent },
			
		],
	},
	{ path: 'signup', canActivate: [adminGuard], component: SignupComponent },
	{ path: '', component: LoginpageComponent },
	{ path: 'list', canActivate: [adminGuard], component: ListComponent },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
