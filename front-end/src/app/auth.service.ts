import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public auth(loginData, apiURL): any{

    return this.http.post(`${ apiURL }/seguranca/loginJWT` , loginData).toPromise();


  }

}
