import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MestresService} from '../services/mestres.service';
import { Mestre } from '../shared/mestre.model';
import {LoggedUserService} from '../logged-user.service';
import {URL_API} from '../app.api';

@Component({
  selector: 'app-mestre',
  templateUrl: './mestre.component.html',
  styleUrls: ['./mestre.component.css']
})
export class MestreComponent implements OnInit {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };

  MESTRES = null;

  constructor(private router:Router, private http : HttpClient, private loggedUserService: LoggedUserService) {

    if( loggedUserService.isLogged() ) {
      var promiseClasses = this.http.get(`${URL_API}/api/mestre`, 
        this.httpOptions)
        .toPromise();
      
      promiseClasses.then((data)=>{
  
        var jsonInfo = JSON.stringify(data);
        var Info = JSON.parse(jsonInfo);
        this.MESTRES = Info.response.data;
        
       
    
      }).catch((error)=>{
        
        console.log("Promise rejected with " + JSON.stringify(error));
      })
     }
    }
  ngOnInit(): void {


    if( !this.loggedUserService.isLogged()) {
      this.router.navigate(['/login']); 
    }


  }

}
