import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrariDipComponent } from './orari-dip.component';

describe('OrariDipComponent', () => {
  let component: OrariDipComponent;
  let fixture: ComponentFixture<OrariDipComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrariDipComponent]
    });
    fixture = TestBed.createComponent(OrariDipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
