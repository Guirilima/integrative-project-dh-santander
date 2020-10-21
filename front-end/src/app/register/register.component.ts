import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, Validators } from '@angular/forms';
import {NgForm} from '@angular/forms';





@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
  
})



export class RegisterComponent implements OnInit {

  cidade;
  estado;
  nome = new FormControl(null,[
    Validators.required, Validators.minLength(4),]);
  sobrenome = new FormControl('');
  email = new FormControl('');
  nomeUsuario = new FormControl('');
  cpf = new FormControl('');
  cep = new FormControl('');
  dataNascimento = new FormControl('');
  telefone = new FormControl('');
  senha = new FormControl('');
  genero = new FormControl('');


  ngOnInit(): void {
    
}



readonly apiURL : string; //url base 


constructor(private http : HttpClient) {

  this.apiURL = 'http://localhost:8080';
  this.cidade = '';
  this.estado = '';

}

parseDataNascimento(data:string)
{

var dia = data.slice(0, 2);
var mes = data.slice(2, 4);
var ano = data.slice(4, 8);

var dataParse = mes + "-" + dia + "-" + ano;

return dataParse;

}

cadastrarUsuario()
{
  console.log("Tetando cadastrar usuário")
  

  //this.parseDataNascimento(this.dataNascimento.value);

  console.log(new Date(this.parseDataNascimento(this.dataNascimento.value)).toISOString())
   
  var usuario = { 
    
    cityUser : this.cidade,
    cpfUser: this.cpf.value,
    dateOfBirth:  "2020-10-20T12:20:30.726Z",
    emailUser: this.email.value,
    genero: this.genero.value,
    lastNameUser: this.sobrenome.value,
    nameUser: this.nomeUsuario.value,
    passwordUser: this.senha.value,
    phoneNumber: this.telefone.value,
    stateUser: this.estado

  }; 

  console.log(usuario);



  this.http.post(`${ this.apiURL }/user/cadastro-criar-usuario` , usuario)
            .subscribe(
              (resultado:any) => {
                
                console.log(resultado.response)
              },
              erro => {
                if(erro.status == 400) {
                  console.log(erro);
                }
              }
            );

}
            

    /*

    {
  "cityUser": "São Paulo",
  "cpfUser": "21743682859",
  "dateOfBirth": "2020-10-20T12:20:30.726Z",
  "emailUser": "guilher@gmail.com",
  "genero": "M",
  "lastNameUser": "Lima",
  "nameUser": "Guilherme",
  "passwordUser": "1234@",
  "phoneNumber": "11940445029",
  "stateUser": "SP"
}
    */



buscarCEP() {

//  console.log(new Date(23111991));


  var produto = { nome : "" }; //TODO isso tem que ser um GET e não um post
  
  console.log(this.cep.value);

  this.http.post(`${ this.apiURL }/endereco/buscar-pelo-cep/${ this.cep.value }` , produto)
            .subscribe(
              (resultado:any) => {
                this.estado = resultado.response.state
                this.cidade = resultado.response.city
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

