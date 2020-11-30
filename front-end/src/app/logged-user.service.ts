import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggedUserService {

  constructor(private http: HttpClient) { }

  public isLogged(){

    if(sessionStorage.getItem('user') === null) return false;
    else return true;

  }

  public getJwt(){

    if(this.isLogged)
    {
      return JSON.parse(sessionStorage.getItem('user')).jwt;
    }
    else return false;
  }

  public getId(){
    if(this.isLogged)
    {
      return JSON.parse(sessionStorage.getItem('user')).idUsuario;
    }
  }

  public getEmail(){
    return JSON.parse(sessionStorage.getItem('user')).email;
  }
}
