import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ConvitesService} from '../services/convites.service';
import {Convite} from '../shared/convite.model';
import {LoggedUserService} from '../logged-user.service';
import {UsuarioService} from '../usuario.service';

@Component({
  selector: 'app-convite',
  templateUrl: './convite.component.html',
  styleUrls: ['./convite.component.css'],
  providers: [ConvitesService]
})
export class ConviteComponent implements OnInit {

  public convitesRecebidos = null;
  public convitesEnviados = null;

  testeString = "TesteString"


  apiURL = 'http://localhost:8080/api';


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };



  constructor(public usuarioService: UsuarioService,private convitesService: ConvitesService, private loggedUserService: LoggedUserService,private http : HttpClient) { }

  ngOnInit(){


    if( this.loggedUserService.isLogged() ) {
      var promiseRecebidos = this.http.get(`${ this.apiURL }/notificacao/usuarioConvidado/${ this.loggedUserService.getId() }`, this.httpOptions).toPromise();
      
      promiseRecebidos.then((data)=>{
  
        var jsonInfo = JSON.stringify(data);
        //console.log(jsonInfo);
        var Info = JSON.parse(jsonInfo);
        this.convitesRecebidos = Info.response.data;
        
       
    
      }).catch((error)=>{
        
        console.log("Promise rejected with " + JSON.stringify(error));
      })
    
    

}
}


  

nomeConvite(id)
{
 console.log("nome no convite");
  return "SilasSiolver";
 // return this.usuarioService.getUserName(id,this.apiURL);
}

}
