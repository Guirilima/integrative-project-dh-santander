import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Personagem } from '../shared/personagem.model';
import { LoggedUserService } from '../logged-user.service';
import { URL_API } from '../app.api';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { BrowserModule } from '@angular/platform-browser'


@Component({
  selector: 'app-personagem',
  templateUrl: './personagem.component.html',
  styleUrls: ['./personagem.component.css']
})


export class PersonagemComponent implements OnInit {


  apiURL = 'http://localhost:8080/api';

  @Input() idRacaPersonagem: any;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${this.loggedUserService.getJwt()}`
    })
  };

  
  NomeClasse;
  NomeRaca;

  PERSONAGENS = null;
  RACAS = null;
  CLASSES = null;
  

  @ViewChild('modalConvite') modalConvite: any;

  constructor(private router: Router, private http: HttpClient, private loggedUserService: LoggedUserService, private modalService: NgbModal, config: NgbModalConfig) {

    if (this.loggedUserService.isLogged()) {
      var promisePersonagem = this.http.get(`${URL_API}/api/personagem?pagina=0&qtdPagina=100`,
        this.httpOptions)
        .toPromise();

      promisePersonagem.then((data) => {

        var jsonInfo = JSON.stringify(data)
        // console.log(jsonInfo);
        var Info = JSON.parse(jsonInfo);
        this.PERSONAGENS = Info.response.data;
        console.log(this.PERSONAGENS);


      }).catch((error) => {

        console.log("Promise rejected with " + JSON.stringify(error));
      })
////
      var promiseNomeRaca = this.http.get(`${URL_API}/api/raca/listar-racas?pagina=0&qtdPagina=100`,
        this.httpOptions)
        .toPromise();

        promiseNomeRaca.then((data) => {

        var jsonInfo = JSON.stringify(data)
        // console.log(jsonInfo);
        var Info = JSON.parse(jsonInfo);
        this.RACAS = Info.response.data;
        console.log(this.RACAS);


      }).catch((error) => {

        console.log("Promise rejected with " + JSON.stringify(error));
      })

      var promiseNomeClasse = this.http.get(`${URL_API}/api/classe/listar-classes`,
      this.httpOptions)
      .toPromise();

      promiseNomeClasse.then((response) => {

      var jsonInfo = JSON.stringify(response)
      // console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.CLASSES = Info.response;
      console.log(this.CLASSES);


    }).catch((error) => {

      console.log("Promise rejected with " + JSON.stringify(error));
    })
////
    }
  }
  ngOnInit(): void {


    if (!this.loggedUserService.isLogged()) {
      this.router.navigate(['/login']);
    }

  }

  salvarPersonagem(idConvidado, idPersonagem) {
    sessionStorage.setItem('idPersonagem', idPersonagem);
    sessionStorage.setItem('idConvidado', idConvidado);
    sessionStorage.setItem('idMestre', null);
    // this.router.navigate((['/convidar']));
  }

  abrirModal() {
    console.log("Tentei abrir");

    this.modalService.open(this.modalConvite);
  }

  proprioPersonagem(idUsuario) {
    if (idUsuario === this.loggedUserService.getId()) return false;
    return true;
  }

  //   print(){
  //    console.log("deu boa")
  //  }

  idClasseToNomeClasse(idClasse){
    
  }
}
