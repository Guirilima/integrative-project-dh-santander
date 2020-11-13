import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCharacterComponent } from './register-character.component';

describe('RegisterCharacterComponent', () => {
  let component: RegisterCharacterComponent;
  let fixture: ComponentFixture<RegisterCharacterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterCharacterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterCharacterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
