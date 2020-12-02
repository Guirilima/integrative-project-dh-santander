import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Personagem } from '../shared/personagem.model';
import {LoggedUserService} from '../logged-user.service';
import {URL_API} from '../app.api';


@Component({
  selector: 'app-personagem',
  templateUrl: './personagem.component.html',
  styleUrls: ['./personagem.component.css']
})
export class PersonagemComponent implements OnInit {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };

  PERSONAGENS = null;

  constructor(private router:Router, private http : HttpClient, private loggedUserService: LoggedUserService) {

    if( this.loggedUserService.isLogged()) {
    var promiseClasses = this.http.get(`${URL_API}/api/personagem`, 
      this.httpOptions)
      .toPromise();
    
    promiseClasses.then((data)=>{

      var jsonInfo = JSON.stringify(data)
      // console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.PERSONAGENS = Info.response.data;
      
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
   }
  }
  ngOnInit(): void {


    if(!this.loggedUserService.isLogged()) {
      this.router.navigate(['/login']); 
    }


  }

  salvarPersonagem(idConvidado, idPersonagem){
    sessionStorage.setItem('idPersonagem',idPersonagem);
    sessionStorage.setItem('idConvidado',idConvidado);
    this.router.navigate((['/convidar']));
  }

  
}
