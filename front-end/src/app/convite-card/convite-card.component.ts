import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { LoggedUserService } from '../logged-user.service';
import {UsuarioService} from '../usuario.service';


@Component({
  selector: 'app-convite-card',
  templateUrl: './convite-card.component.html',
  styleUrls: ['./convite-card.component.css']
})
export class ConviteCardComponent implements OnInit {

  @Input() mensagemConvite: string;
  @Input() idConvidou: any
  @Input() fullNotificacao: any;


  apiURL = 'http://localhost:8080/api';

  WppUrl = null;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };

  NomeConvite = null;
  NomePersonagem = null;

  constructor(public usuarioService: UsuarioService,private loggedUserService: LoggedUserService,private http : HttpClient) { }

  ngOnInit(): void { 

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
      })
    };
  
  
  
    var promiseUsuario = this.http.get(`${ this.apiURL }/usuario/${ this.idConvidou }`, httpOptions).toPromise();
        
    promiseUsuario.then((data)=>{
  
      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.NomeConvite = Info.response.nomePessoal;
         
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })


    if(this.tipoConvite()) {
    var promiseNomePersonagem = this.http.get(`${ this.apiURL }/personagem/${ this.fullNotificacao.idPersonagemConvidado }`, httpOptions).toPromise();
        
    promiseNomePersonagem.then((data)=>{
  
      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.NomePersonagem = Info.response.nomePersonagem;
         
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
  }







     
   }


    

   


  sendWpp()
  {
    var promiseMensagemWpp = this.http.get(`${ this.apiURL }/usuario/dadosEnvioWhatsapp/${ this.idConvidou }`, this.httpOptions).toPromise();
    
    promiseMensagemWpp.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.WppUrl = Info.response.urlApiWhatsapp;
      console.log(this.WppUrl);
      window.open(String(this.WppUrl),'_blank')

      
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })


  }

  recusarConvite(){

    var conviteAlterado = this.fullNotificacao;
    conviteAlterado.flagSituacaoConvite = "R";

    var promiseNotificacaoPut = this.http.delete(`${ this.apiURL }/notificacao/${ this.fullNotificacao.idNotificacao }`, this.httpOptions).toPromise();
    
    
    promiseNotificacaoPut.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
     //¹ console.log(conviteAlterado);
      
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })

  }


  tipoConvite()
  {
    if(this.fullNotificacao.destinoNotificacao === "Personagem") return true;
    else return false;
  }

  getNomeConvite(_id: any)
{

   //console.log("Cheguei aqui de novo")
  return this.usuarioService.getUserName(_id,this.apiURL);

  //return "Silão"
}

}
