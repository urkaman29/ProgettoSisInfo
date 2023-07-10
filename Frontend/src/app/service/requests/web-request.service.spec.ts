import { TestBed } from '@angular/core/testing';

import { WebRequestService } from './webRequest.service';

describe('WebRequestService', () => {
	let service: WebRequestService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(WebRequestService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
