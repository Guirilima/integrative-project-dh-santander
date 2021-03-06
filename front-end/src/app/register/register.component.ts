import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router'; 
import {UsuarioService} from '../usuario.service';
import {CepService} from '../cep.service';


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


constructor(private cepService: CepService,private usuarioService: UsuarioService,private http : HttpClient,config: NgbModalConfig, private modalService: NgbModal, private router:Router) {

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

    
  request.then((data)=>{

    var jsonInfo = JSON.stringify(data);
    //console.log(jsonInfo);
    var Info = JSON.parse(jsonInfo);
    console.log(Info.response.status)

    this.tituloModal = "Sucesso ! ";
    this.mensagemModal = "Usuário Cadastrado !";
    console.log(this.formRegistro.value);
    this.open(this.modalConfirma);
    console.log("O formulário foi ensiado com sucesso");

     
  this.formRegistro.get('nome').setValue('');
  this.formRegistro.get('sobrenome').setValue('');
  this.formRegistro.get('email').setValue('');
  this.formRegistro.get('nomeUsuario').setValue('');
  this.formRegistro.get('cpf').setValue('');
  this.formRegistro.get('cep').setValue('');
  this.formRegistro.get('dataNascimento').setValue('');
  this.formRegistro.get('telefone').setValue('');
  this.formRegistro.get('senha').setValue('');
  this.formRegistro.get('genero').setValue('');

  this.router.navigate(['/login']); 

  }).catch((error)=>{
    
    console.log("Promise rejected with " + JSON.stringify(error));
    var Info = JSON.parse(JSON.stringify(error));
    this.tituloModal = "Erro ! ";

    this.mensagemModal = Info.error.mensagem;
    console.log(this.formRegistro.value);
    this.open(this.modalConfirma);
    console.log("O formulário contem erros")
  })
    

   
  }
}

cadastrarUsuario()
{
  console.log("Tentando cadastrar usuário")
  

  //this.parseDataNascimento(this.dataNascimento.value);

   
  var usuario = { 
    
    role: 1,
    nomeUsur: this.formRegistro.get('nomeUsuario').value,
    sobrenomeUsur: this.formRegistro.get('sobrenome').value,
    nascimentoUsur:  "2020-10-20T12:20:30.726Z",
    generoUsur: this.formRegistro.get('genero').value,
    emailUsur: this.formRegistro.get('email').value,
    senhaUsur: this.formRegistro.get('senha').value,
    cpfUsur: this.formRegistro.get('cpf').value,
    telefoneUsur: this.formRegistro.get('telefone').value,
    estadoUsur: this.estado,
    cidadeUsur : this.cidade,
    flagAtivoUsur: 1
    

  }; 

  return this.usuarioService.postUsuario(usuario,this.apiURL);

}
            
buscarCEP() {

  var produto = { nome : "" }; //TODO isso tem que ser um GET e não um post
  
  var promise = this.cepService.getCep(this.apiURL,this.formRegistro.get('cep').value,produto)
  
  promise.then((data)=>{
    var jsonInfo = JSON.stringify(data);
    var Info = JSON.parse(jsonInfo);
    this.estado = Info.response.state
    this.cidade = Info.response.city
    console.log(this.cidade);
    if(this.cidade == null || this.estado == null) 
    {
      console.log("CEP inválido !");
      this.tituloModal = "Erro ! ";
      this.mensagemModal = "CEP Inválido !";
      console.log(this.formRegistro.value);
      this.open(this.modalConfirma);
      this.formRegistro.get('cep').setValue('');

    }

  }).catch((error)=>{
    console.log("Promise rejected with " + JSON.stringify(error));
  });
  
}}

