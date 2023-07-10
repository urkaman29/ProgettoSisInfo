import { CapitalisePipe } from './pipes/capitalise.pipe';

describe('CapitalisePipe', () => {
	it('create an instance', () => {
		const pipe = new CapitalisePipe();
		expect(pipe).toBeTruthy();
	});
});
