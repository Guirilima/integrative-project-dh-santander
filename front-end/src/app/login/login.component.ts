import { Component, NgModule, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';




@Component({

  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})




export class LoginComponent implements OnInit {

  formLogin = new FormGroup({
  email : new FormControl(null, [Validators.required,Validators.email]),
  senha: new FormControl(null,Validators.required),
  })

  constructor() { }

  ngOnInit(): void {
  }

}


