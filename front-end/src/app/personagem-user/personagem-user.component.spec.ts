import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonagemUserComponent } from './personagem-user.component';

describe('PersonagemUserComponent', () => {
  let component: PersonagemUserComponent;
  let fixture: ComponentFixture<PersonagemUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonagemUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonagemUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
