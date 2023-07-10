import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrariTitComponent } from './orari-tit.component';

describe('OrariDipComponent', () => {
  let component: OrariTitComponent;
  let fixture: ComponentFixture<OrariTitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrariTitComponent]
    });
    fixture = TestBed.createComponent(OrariTitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
