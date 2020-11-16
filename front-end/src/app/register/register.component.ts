import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, Validators, FormGroup, AbstractControl } from '@angular/forms';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
})

export class RegisterComponent implements OnInit {

  cidade;
  estado;
  @ViewChild('modalConfirma') modalConfirma;
  tituloModal;
  mensagemModal;
  
  formRegistro = new FormGroup({
  nome : new FormControl(null,[
  Validators.required, Validators.minLength(4),]),
  sobrenome : new FormControl(null, Validators.required),
  email : new FormControl(null,[Validators.required,Validators.email]),
  nomeUsuario : new FormControl(null, Validators.required),
  cpf : new FormControl(null, [Validators.required,Validators.minLength(11)]),
  cep : new FormControl(null,[Validators.required,Validators.minLength(8)]),
  dataNascimento : new FormControl(null,Validators.minLength(8)),
  telefone : new FormControl(null,Validators.minLength(10)),
  senha : new FormControl(null,[Validators.required, Validators.minLength(8)]),
  genero : new FormControl(null,Validators.required),

}, {

})



  ngOnInit(): void {

    this.formRegistro.addControl('confirmaSenha', new FormControl(null, [Validators.compose(
      [Validators.required, this.validateAreEqual.bind(this)])]))
   
}

open(content) {
  this.modalService.open(content);
}

  validateAreEqual(fieldControl: FormControl) {
  return fieldControl.value === this.formRegistro.get("senha").value ? null : {
      NotEqual: true
  };
}


readonly apiURL : string; //url base 

  passwordMatchValidator(control: FormControl) {
  const password: string = control.get('senha').value; // get password from our password form control
  const confirmPassword: string = control.get('confirmaSenha').value; // get password from our confirmPassword form control
  // compare is the password math
  return (password !== confirmPassword) ?  null : {
    validatePassword: false,
    
    // if they don't match, set an error in our confirmPassword form control
 
}}


constructor(private http : HttpClient,config: NgbModalConfig, private modalService: NgbModal) {

  this.apiURL = 'http://localhost:8080/api';
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

onSubmit() {
  // aqui você pode implementar a logica para fazer seu formulário salvar
  if(this.formRegistro.valid)
  {

   // console.log(this.formRegistro.value);
    var request = this.cadastrarUsuario();
    console.log(request);
    
    this.tituloModal = "Confirma  ! ";
    this.mensagemModal = "Usuário Cadastrado ! ";
    console.log(this.formRegistro.value);
    this.open(this.modalConfirma);
    console.log("Formulário Correto")

  } 
  else
  {
    this.tituloModal = "Erro ! ";
    this.mensagemModal = "Formulário Contém Erros !";
    console.log(this.formRegistro.value);
    this.open(this.modalConfirma);
    console.log("O formulário contem erros")
}

}

cadastrarUsuario()
{
  console.log("Tentando cadastrar usuário")
  

  //this.parseDataNascimento(this.dataNascimento.value);

   
  var usuario = { 
    
    cidadeUsur : this.cidade,
    cpfUsur: this.formRegistro.get('cpf').value,
    nascimentoUsur:  "2020-10-20T12:20:30.726Z",
    emailUsur: this.formRegistro.get('email').value,
    generoUsur: this.formRegistro.get('genero').value,
    sobrenomeUsur: this.formRegistro.get('sobrenome').value,
    nomeUsur: this.formRegistro.get('nomeUsuario').value,
    senhaUsur: this.formRegistro.get('senha').value,
    telefoneUsur: this.formRegistro.get('telefone').value,
    estadoUsur: this.estado,

  }; 

  //console.log(usuario);

  var respostaReq = {
      tipo: null,
      content: null
  }

   this.http.post(`${ this.apiURL }/usuario` , usuario)
            .subscribe(
              (resultado:any) => {
                respostaReq.tipo = 0;
                respostaReq.content = "Usuário salvo com sucesso";
               // console.log(resultado);
                return resultado;
              },
              erro => {
                respostaReq.tipo = 1;
                if(erro.status == 400) {
                 // console.log(erro);
                  respostaReq.content = "Erro durante o salvamento e/ou manipulação dos dados";
                  return erro;             
                }
              }
            );
}
            


buscarCEP() {



  var produto = { nome : "" }; //TODO isso tem que ser um GET e não um post
  
  console.log(this.formRegistro.get('cep').value);

  this.http.post(`${ this.apiURL }/endereco/buscar-pelo-cep/${ this.formRegistro.get('cep').value }` , produto)
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

