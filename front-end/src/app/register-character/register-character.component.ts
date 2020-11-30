import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {LoggedUserService} from '../logged-user.service'
import { options } from '../app.module';

@Component({
  selector: 'app-register-character',
  templateUrl: './register-character.component.html',
  styleUrls: ['./register-character.component.css']
})
export class RegisterCharacterComponent implements OnInit {


   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUser.getJwt() }`
    })
  };


  
  CLASSES = null;
  RACAS = null;
  
  apiURL = 'http://localhost:8080/api';

  formRegistroPersonagem = new FormGroup({
    nomePersonagem : new FormControl(null,[Validators.required,Validators.minLength(4)]),
    racaPersonagem: new FormControl(null),
    classePersonagem: new FormControl(null),
    historiaPersonagem: new FormControl(null,[Validators.required,Validators.minLength(150)])});

  constructor(private http : HttpClient, private loggedUser: LoggedUserService) {

  
    
    var promiseClasses = this.http.get(`${ this.apiURL }/classe/select-listar-classes`,this.httpOptions).toPromise();
    
    promiseClasses.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.CLASSES = Info.response;
      
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })

 
    
    var promiseRacas = this.http.get(`${ this.apiURL }/raca/select-listar-racas`, this.httpOptions).toPromise();
    
    promiseRacas.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      console.log(Info.response)
      this.RACAS = Info.response;
      
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })



   }

   onSubmit() {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: `Bearer ${ this.loggedUser.getJwt() }`
      })
    };
    // aqui você pode implementar a logica para fazer seu formulário salvar
    console.log("Okay");
    if(this.formRegistroPersonagem.valid)
    {
      var personagem = {
      historiaPersonagem: this.formRegistroPersonagem.get('historiaPersonagem').value,
      idClasse: this.formRegistroPersonagem.get('classePersonagem').value,
      idRaca: this.formRegistroPersonagem.get('racaPersonagem').value,
      idUsuario: this.loggedUser.getId(),
      nomePersonagem: this.formRegistroPersonagem.get('nomePersonagem').value
    }

    console.log(personagem);

    var request = this.http.post(`${ this.apiURL }/personagem` ,personagem, this.httpOptions).toPromise();

    request.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      console.log(Info.response.status);

      this.formRegistroPersonagem.get('historiaPersonagem').setValue('');
      this.formRegistroPersonagem.get('nomePersonagem').setValue('');
      
      this.formRegistroPersonagem.get('historiaPersonagem').untouched;
      this.formRegistroPersonagem.get('nomePersonagem').untouched;
      

  
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
      var Info = JSON.parse(JSON.stringify(error));
      console.log(Info);
      
    })
      
  
     
    }

    else{
      console.log("erros");
    }
  }


  ngOnInit(): void {
  }

}
