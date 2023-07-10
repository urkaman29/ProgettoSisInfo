import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth/authservice.service';

export const adminGuard: CanActivateFn = (route, state) => {
	const router: Router = inject(Router);
	const authService: AuthService = inject(AuthService);

	const adminRoles = ['titolare'];
	return (
		adminRoles.some((role) => authService.userHasRole(role)) || router.navigate(['/'])
	);
};
