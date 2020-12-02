import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import {LoggedUserService} from '../logged-user.service';

@Component({
  selector: 'app-personagem-user',
  templateUrl: './personagem-user.component.html',
  styleUrls: ['./personagem-user.component.css']
})
export class PersonagemUserComponent implements OnInit {


  apiURL = 'http://localhost:8080/api';


  @Input() nomePersonagem: string;
  @Input() historiaPersonagem: string;
  @Input() classePersonagem: string;
  @Input() racaPersonagem: string;
  @Input() idPersonagem: any;


  constructor(private http : HttpClient, private loggedUserService: LoggedUserService) { }


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };


  ngOnInit(): void {
  }

  deletarPersonagem(){

    var promiseDelete = this.http.delete(`${ this.apiURL }/personagem/${ this.idPersonagem }`, this.httpOptions).toPromise();
    
    promiseDelete.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      location.reload();
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
   }


}
