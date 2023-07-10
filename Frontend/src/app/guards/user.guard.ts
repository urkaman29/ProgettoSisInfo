import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth/authservice.service';

export const userGuard: CanActivateFn = (route, state) => {
	const router: Router = inject(Router);
	const authService: AuthService = inject(AuthService);

	const userRoles = ['magazziniere', 'meccanico', 'dipendente'];
	return (
		userRoles.some((role) => authService.userHasRole(role)) || router.navigate(['/'])
	);
};
