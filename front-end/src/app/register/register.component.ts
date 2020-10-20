import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  cidade;
  estado;
 
  ngOnInit(): void {
    
     

 
}

  

readonly apiURL : string; //url base 


constructor(private http : HttpClient) {

  this.apiURL = 'http://localhost:8080';
  this.cidade = '';
  this.estado = '';

}


buscarCEP() {

  var cep = (<HTMLInputElement>document.getElementById("cepInputID")).value;

  var produto = { nome : "" }; //TODO isso tem que ser um GET e não um post
  
  console.log(cep);

  this.http.post(`${ this.apiURL }/endereco/buscar-pelo-cep/${ cep }` , produto)
            .subscribe(
              (resultado:any) => {
                this.estado = (JSON.stringify(resultado.response.city))
                this.cidade = (JSON.stringify(resultado.response.state))
                if(this.cidade == "null" || this.estado == "null") console.log("CEP inválido !");

                console.log(this.estado)
              },
              erro => {
                if(erro.status == 400) {
                  console.log(erro);
                }
              }
            );



            }}
