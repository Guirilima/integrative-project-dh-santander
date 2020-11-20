import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMasterComponent } from './register-master.component';

describe('RegisterMasterComponent', () => {
  let component: RegisterMasterComponent;
  let fixture: ComponentFixture<RegisterMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
