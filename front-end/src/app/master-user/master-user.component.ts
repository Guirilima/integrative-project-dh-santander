import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import {LoggedUserService} from '../logged-user.service';


@Component({
  selector: 'app-master-user',
  templateUrl: './master-user.component.html',
  styleUrls: ['./master-user.component.css']
})
export class MasterUserComponent implements OnInit {



  apiURL = 'http://localhost:8080/api';


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };


  @Input() anosExperiencia: any;
  @Input() qtdCampanhas: any;
  @Input() resumoMestre: string;
  @Input() idMestre: any;

  constructor(private http : HttpClient,private loggedUserService: LoggedUserService) { }



  ngOnInit(): void {
  }


  deletarMestre()
  {

    var promiseDelete = this.http.delete(`${ this.apiURL }/mestre/${ this.idMestre }`, this.httpOptions).toPromise();
    
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
