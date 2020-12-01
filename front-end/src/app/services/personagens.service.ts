import { HttpClient,HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Personagem } from '../shared/personagem.model';
import {URL_API} from '../app.api';
import {LoggedUserService} from '../logged-user.service';

// import 'rxjs/add/operator/toPromise';


@Injectable()
export class PersonagensService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: `Bearer ${ this.loggedUserService.getJwt() }`
    })
  };

  constructor(private http: HttpClient,private loggedUserService: LoggedUserService) { }

  public getPersonagens(): Promise<Personagem[]> {
    
    var promiseClasses = this.http.get( `${URL_API}/api/personagem`,this.httpOptions)
      .toPromise()
      .then((resposta: any) => resposta)

      return promiseClasses
  }
  
}