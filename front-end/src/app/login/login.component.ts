import { Component, NgModule, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService} from '../auth.service';




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

  readonly apiURL : string; //url base 
   
  constructor(private authService: AuthService ) {

    this.apiURL = 'http://localhost:8080/api';

   }

  ngOnInit(): void {
  }

  onSubmit()
  {

    var loginData = {

      senha: this.formLogin.get('senha').value,
      username: this.formLogin.get('email').value

    }

   var request =  this.authService.auth(loginData,this.apiURL);

   request.then((data) =>{

    var jsonInfo = JSON.stringify(data);
    //console.log(jsonInfo);
    var Info = JSON.parse(jsonInfo);
    console.log(Info.response.status)


   }).catch((error) =>{

    


   })
  }



}


