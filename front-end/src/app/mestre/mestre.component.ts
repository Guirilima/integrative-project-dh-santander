import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {MestresService} from '../services/mestres.service';
import { Mestre } from '../shared/mestre.model';
import {LoggedUserService} from '../logged-user.service';
import {URL_API} from '../app.api';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-mestre',
  templateUrl: './mestre.component.html',
  styleUrls: ['./mestre.component.css']
})
export class MestreComponent implements OnInit {

  apiURL = 'http://localhost:8080/api';


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };

  MESTRES = null;
  NOME = null;

  @ViewChild('modalConvite') modalConvite: any;

  constructor(private router:Router, private http : HttpClient, private loggedUserService: LoggedUserService, private modalService: NgbModal, config: NgbModalConfig) {

    if( loggedUserService.isLogged() ) {
      var promiseClasses = this.http.get(`${this.apiURL}/mestre`, 
        this.httpOptions)
        .toPromise();
      
      promiseClasses.then((data)=>{
  
        var jsonInfo = JSON.stringify(data);
        var Info = JSON.parse(jsonInfo);
        this.MESTRES = Info.response.data;
        // console.log(this.MESTRES);

       
    
      }).catch((error)=>{
        
        console.log("Promise rejected with " + JSON.stringify(error));
      })

      var promiseNome = this.http.get(`${URL_API}/api/usuario?pagina=0&qtdPagina=100`,
        this.httpOptions)
        .toPromise();

        promiseNome.then((data) => {

        var jsonInfo = JSON.stringify(data)
        // console.log(jsonInfo);
        var Info = JSON.parse(jsonInfo);
        this.NOME= Info.response.data;
        // console.log(this.RACAS[0].nomeRaca);
        // console.log(this.RACAS[0].idRaca);
        // console.log(this.NOME);


      }).catch((error) => {

        console.log("Promise rejected with " + JSON.stringify(error));
      })

     }
    }
  ngOnInit(): void {


    if( !this.loggedUserService.isLogged()) {
      this.router.navigate(['/login']); 
    }


  }

  salvarMestre(idConvidado, idMestre){
    sessionStorage.setItem('idMestre',idMestre);
    sessionStorage.setItem('idConvidado',idConvidado);
    sessionStorage.setItem('idPersonagem',null);
    // this.router.navigate((['/convidar']));
  }

  getUserName(id)
  {
    
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
      })
    };
  
  
  
    var promiseUsuario = this.http.get(`${ this.apiURL }/usuario/${ id }`, httpOptions).toPromise();
        
    promiseUsuario.then((data)=>{
  
      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      return Info.response.nomePessoal;
         
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
  }

  abrirModal() {
    // console.log("Tentei abrir");

    this.modalService.open(this.modalConvite);
  }

  proprioMestre(idMestre)
  {
    if(idMestre === this.loggedUserService.getId()) return false;
    return true;
  }


  idUsuarioToNome(idUsuario){
    var tamanho = this.NOME.length;
    for (let i = 0; i <tamanho; i++){
      if(this.NOME[i].idUsuario == idUsuario){
        return this.NOME[i].nomePessoal;
      }
    }
  }
}
