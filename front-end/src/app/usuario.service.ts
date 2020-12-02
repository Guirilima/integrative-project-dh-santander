import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {LoggedUserService} from './logged-user.service';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient,private loggedUserService: LoggedUserService) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };



  public postUsuario(usuario, apiURL): any{

    return this.http.post(`${ apiURL }/usuario` , usuario).toPromise();

 }

 public getUserName(id, apiURL)
 {

  var promiseUsuario = this.http.get(`${ apiURL }/usuario/${ id }`, this.httpOptions).toPromise();
      
  promiseUsuario.then((data)=>{

    var jsonInfo = JSON.stringify(data);
    //console.log(jsonInfo);
    var Info = JSON.parse(jsonInfo);
    return Info.response.nomePessoal;
    
   

  }).catch((error)=>{
    
    console.log("Promise rejected with " + JSON.stringify(error));
  })
   
 }
}
