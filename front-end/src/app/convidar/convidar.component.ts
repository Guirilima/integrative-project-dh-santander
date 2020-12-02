import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {LoggedUserService} from '../logged-user.service'
import { options } from '../app.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-convidar',
  templateUrl: './convidar.component.html',
  styleUrls: ['./convidar.component.css']
})
export class ConvidarComponent implements OnInit {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUser.getJwt() }`
    })
  };


  
  // CLASSES = null;
  // RACAS = null;
  
  apiURL = 'http://localhost:8080/api';

  formRegistroConvidar = new FormGroup({
    // nomePersonagem : new FormControl(null,[Validators.required,Validators.minLength(4)]),
    // racaPersonagem: new FormControl(null),
    // classePersonagem: new FormControl(null),
    descricaoConvite: new FormControl(null)});

  constructor(private http : HttpClient, private loggedUser: LoggedUserService, private router:Router,) {

  
    
    // var promiseClasses = this.http.get(`${ this.apiURL }/classe/select-listar-classes`,this.httpOptions).toPromise();
    
    // promiseClasses.then((data)=>{

    //   var jsonInfo = JSON.stringify(data);
    //   //console.log(jsonInfo);
    //   var Info = JSON.parse(jsonInfo);
    //   this.CLASSES = Info.response;
      
     
  
    // }).catch((error)=>{
      
    //   console.log("Promise rejected with " + JSON.stringify(error));
    // })

 
    
    // var promiseRacas = this.http.get(`${ this.apiURL }/raca/select-listar-racas`, this.httpOptions).toPromise();
    
    // promiseRacas.then((data)=>{

    //   var jsonInfo = JSON.stringify(data);
    //   //console.log(jsonInfo);
    //   var Info = JSON.parse(jsonInfo);
    //   console.log(Info.response)
    //   this.RACAS = Info.response;
      
     
  
    // }).catch((error)=>{
      
    //   console.log("Promise rejected with " + JSON.stringify(error));
    // })



   }

   onSubmit() {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: `Bearer ${ this.loggedUser.getJwt() }`
      })
    };
    // aqui você pode implementar a logica para fazer seu formulário salvar
    console.log("Okay");
    if(this.formRegistroConvidar.valid)
    {
      var convidar = {
        descricaoConvite: this.formRegistroConvidar.get('descricaoConvite').value,
        flagMeioConvite: "W",
        idMestreConvidado: sessionStorage.getItem('idMestre'),
        idPersonagemConvidado: sessionStorage.getItem('idPersonagem'),
        idUsuarioConvidado: sessionStorage.getItem('idConvidado'),
        idUsuarioConvidou:this.loggedUser.getId()
    }

    console.log(convidar);

    var request = this.http.post(`${ this.apiURL }/notificacao` ,convidar, this.httpOptions).toPromise();

    request.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      console.log(Info.response.status);

      this.formRegistroConvidar.get('descricaoConvite').setValue('');
      // this.formRegistroPersonagem.get('nomePersonagem').setValue('');
      
      this.formRegistroConvidar.get('descricaoConvite').untouched;
      // this.formRegistroPersonagem.get('nomePersonagem').untouched;
      
      this.router.navigate((['/personagem']));

  
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
      var Info = JSON.parse(JSON.stringify(error));
      console.log(Info);
      
    })
      
  
     
    }

    else{
      console.log("erros");
    }
  }


  ngOnInit(): void {
  }

}


// import { Component, Input, OnInit } from '@angular/core';
// import { LoggedUserService } from '../logged-user.service';
// import { URL_API } from '../app.api';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Router } from '@angular/router';
// import { UsuarioService } from '../usuario.service';

// import { FormControl, FormGroup, Validators } from '@angular/forms';

// import { options } from '../app.module';


// @Component({
//   selector: 'app-convidar',
//   templateUrl: './convidar.component.html',
//   styleUrls: ['./convidar.component.css']
// })
// export class ConvidarComponent implements OnInit {
//   httpOptions = {
//     headers: new HttpHeaders({
//       'Content-Type': 'application/json',
//       Authorization: `Bearer ${this.loggedUser.getJwt()}`
//     })
//   };

//   apiURL = 'http://localhost:8080/api';

//   formRegistroConvite = new FormGroup({
//     nomeDeQuemConvidou: new FormControl(null),
//     nomeDoConvidado: new FormControl(null),
//     descricaoConvite: new FormControl(null)
//   });

//   constructor(private http: HttpClient, private loggedUser: LoggedUserService) {}

//   onSubmit() {

//     const httpOptions = {
//       headers: new HttpHeaders({
//         'Content-Type': 'application/json',
//         Authorization: `Bearer ${this.loggedUser.getJwt()}`
//       })
//     };
//     console.log("Okay");
//     if (this.formRegistroConvite.valid) {
//       var convite = {
//         descricaoConvite: this.formRegistroConvite.get('descricaoConvite').value,
//         idUsuarioConvidado: this.formRegistroConvite.get('idUsuarioConvidado').value,
//         idUsuarioConvidou: this.formRegistroConvite.get('idUsuarioConvidou').value
//       }

//       console.log(convite);

//       var request = this.http.post(`${this.apiURL}/convidar`, convite, this.httpOptions).toPromise();

//       request.then((data) => {

//         var jsonInfo = JSON.stringify(data);
//         //console.log(jsonInfo);
//         var Info = JSON.parse(jsonInfo);
//         console.log(Info.response.status);

//         this.formRegistroConvite.get('descricaoConvite').setValue('');
//         this.formRegistroConvite.get('idUsuarioConvidado').setValue('');

//         this.formRegistroConvite.get('descricaoConvite').untouched;
//         this.formRegistroConvite.get('idUsuarioConvidado').untouched;




//       }).catch((error) => {

//         console.log("Promise rejected with " + JSON.stringify(error));
//         var Info = JSON.parse(JSON.stringify(error));
//         console.log(Info);

//       })



//     }

//     else {
//       console.log("erros");
//     }
//   }


//   ngOnInit(): void {
//   }

// }
