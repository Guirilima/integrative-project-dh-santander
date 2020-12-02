import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { LoggedUserService } from '../logged-user.service';

@Component({
  selector: 'app-convite-card',
  templateUrl: './convite-card.component.html',
  styleUrls: ['./convite-card.component.css']
})
export class ConviteCardComponent implements OnInit {

  @Input() nomeConvite: string;
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

  constructor(private loggedUserService: LoggedUserService,private http : HttpClient) { }

  ngOnInit(): void {

    console.log(this.fullNotificacao)
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
      
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })

    window.open(String(this.WppUrl),'_blank')

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

}
