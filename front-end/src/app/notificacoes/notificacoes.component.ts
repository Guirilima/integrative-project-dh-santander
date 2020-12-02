import { Component, OnInit } from '@angular/core';
import {UsuarioService} from '../usuario.service';
import {LoggedUserService} from '../logged-user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-notificacoes',
  templateUrl: './notificacoes.component.html',
  styleUrls: ['./notificacoes.component.css']
})
export class NotificacoesComponent implements OnInit {

  public convitesRecebidos = null;
  public convitesEnviados = null;

  public testeString = "Teste"
  apiURL = 'http://localhost:8080/api';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };

  constructor(public usuarioService: UsuarioService, private loggedUserService: LoggedUserService,private http : HttpClient) { }

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


  

getNomeConvite(_id: any)
{
 // return this.usuarioService.getUserName(id,this.apiURL);
   console.log(this.convitesRecebidos);

  return "Silão"
}

statusNotificacao(flag)
{
  if(flag === "R") return false;
  else return true;
}



}
