import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-personagem-user',
  templateUrl: './personagem-user.component.html',
  styleUrls: ['./personagem-user.component.css']
})
export class PersonagemUserComponent implements OnInit {


  apiURL = 'http://localhost:8080/api';


  @Input() nomePersonagem: string;
  @Input() historiaPersonagem: string;
  @Input() classePersonagem: string;
  @Input() racaPersonagem: string;
  @Input() idPersonagem: any;


  constructor(private http : HttpClient) { }

  ngOnInit(): void {
  }

  deletarPersonagem(){

    var promiseDelete = this.http.delete(`${ this.apiURL }/personagem/${ this.idPersonagem }`).toPromise();
    
    promiseDelete.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      
     
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
    })
   }


}
