import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-character',
  templateUrl: './register-character.component.html',
  styleUrls: ['./register-character.component.css']
})
export class RegisterCharacterComponent implements OnInit {

  formRegistroPersonagem = new FormGroup({
    nomePersonagem : new FormControl(null,[Validators.required,Validators.minLength(4)]),
    racaPersonagem: new FormControl(null,Validators.required),
    classePersonagem: new FormControl(null,Validators.required),
    historiaPersonagem: new FormControl(null,[Validators.required,Validators.minLength(150)])});



  constructor() { }

  ngOnInit(): void {
  }

}
