import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-master',
  templateUrl: './register-master.component.html',
  styleUrls: ['./register-master.component.css']
})
export class RegisterMasterComponent implements OnInit {


   
  formRegistroMestre = new FormGroup({
    anosExperiencia : new FormControl(null),
    qtdCampanhas: new FormControl(null),
    resumoMestre: new FormControl(null,[Validators.required,Validators.minLength(140)])});

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {}

}
