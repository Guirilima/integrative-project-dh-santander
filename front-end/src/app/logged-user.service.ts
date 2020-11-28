import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggedUserService {

  constructor(private http: HttpClient) { }

  public isLogged(){
    return sessionStorage.getItem('user');
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
