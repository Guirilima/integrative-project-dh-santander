import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvidarComponent } from './convidar.component';

describe('ConvidarComponent', () => {
  let component: ConvidarComponent;
  let fixture: ComponentFixture<ConvidarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConvidarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConvidarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
