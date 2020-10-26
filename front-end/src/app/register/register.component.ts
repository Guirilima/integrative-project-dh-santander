import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, Validators, FormGroup, AbstractControl } from '@angular/forms';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';





@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
  
})




export class RegisterComponent implements OnInit {

  cidade;
  estado;
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

onSubmit() {
  // aqui você pode implementar a logica para fazer seu formulário salvar
  if(this.formRegistro.valid) console.log(this.formRegistro.value);
  else console.log("O formulário contem erros")

}

cadastrarUsuario()
{
  console.log("Tentando cadastrar usuário")
  

  //this.parseDataNascimento(this.dataNascimento.value);

   
  var usuario = { 
    
    cityUser : this.cidade,
    cpfUser: this.formRegistro.get('cep').value,
    dateOfBirth:  "2020-10-20T12:20:30.726Z",
    emailUser: this.formRegistro.get('email').value,
    genero: this.formRegistro.get('genero').value,
    lastNameUser: this.formRegistro.get('sobrenome').value,
    nameUser: this.formRegistro.get('nomeUsuario').value,
    passwordUser: this.formRegistro.get('senha').value,
    phoneNumber: this.formRegistro.get('telefone').value,
    stateUser: this.formRegistro.get('estado').value

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

