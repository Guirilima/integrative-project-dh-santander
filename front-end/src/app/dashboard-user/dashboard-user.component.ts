import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {LoggedUserService} from '../logged-user.service';


@Component({
  selector: 'app-dashboard-user',
  templateUrl: './dashboard-user.component.html',
  styleUrls: ['./dashboard-user.component.css']
})
export class DashboardUserComponent implements OnInit {

  PERSONAGENS = null;


  @ViewChild('modalCadastro') modalCadastro: any;

  

  constructor(config: NgbModalConfig, private modalService: NgbModal, private http : HttpClient, private loggedUserService: LoggedUserService) {

    var promiseClasses = this.http.get(`${ this.apiURL }/personagem/buscarPorUsuario/${ this.loggedUserService.getId() }`).toPromise();
    
    promiseClasses.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      this.PERSONAGENS = Info.response.data;
      
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
   }


  apiURL = 'http://localhost:8080/api';


  

  ngOnInit(): void {
  }


  
  abrirModal(){
    console.log("Tentei abrir")

    this.modalService.open(this.modalCadastro);
  }

  


  

 
}
