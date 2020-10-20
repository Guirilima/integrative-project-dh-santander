import { Component, NgModule, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgxMaskModule } from 'ngx-mask'





@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
  
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

cadastrarUsuario()
{
  
  var produto = { 
    
    cityUser : "",
    cpfUser: "",
    emailUser: "",



}; //TODO isso tem que ser um GET e não um post



}



buscarCEP() {

  var cep = (<HTMLInputElement>document.getElementById("idInputCep")).value;

  var produto = { nome : "" }; //TODO isso tem que ser um GET e não um post
  
  console.log(cep);

  this.http.post(`${ this.apiURL }/endereco/buscar-pelo-cep/${ cep }` , produto)
            .subscribe(
              (resultado:any) => {
                this.estado = resultado.response.city
                this.cidade = resultado.response.state
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

