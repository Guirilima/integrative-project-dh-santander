import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {LoggedUserService} from '../logged-user.service';



@Component({
  selector: 'app-dashboard-user',
  templateUrl: './dashboard-user.component.html',
  styleUrls: ['./dashboard-user.component.css']
})
export class DashboardUserComponent implements OnInit {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };


  PERSONAGENS = null;


  @ViewChild('modalCadastro') modalCadastro: any;

  

  constructor(private router:Router, config: NgbModalConfig, private modalService: NgbModal, private http : HttpClient, private loggedUserService: LoggedUserService) {

    if( loggedUserService.isLogged() ) {
    var promiseClasses = this.http.get(`${ this.apiURL }/personagem/buscarPorUsuario/${ this.loggedUserService.getId() }`, this.httpOptions).toPromise();
    
    promiseClasses.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.PERSONAGENS = Info.response.data;
      
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
   }
  }

  apiURL = 'http://localhost:8080/api';


  

  ngOnInit(): void {


    if( !this.loggedUserService.isLogged()) {

      console.log("Usuário não está logado")
      this.router.navigate(['/login']); 

    }


  }


  
  abrirModal(){
    console.log("Tentei abrir")

    this.modalService.open(this.modalCadastro);
  }

  


  

 
}
