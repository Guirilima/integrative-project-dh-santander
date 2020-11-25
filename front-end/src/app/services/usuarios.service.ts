import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Usuario } from '../shared/usuario.model';
import {URL_API} from '../app.api';

// import 'rxjs/add/operator/toPromise';


@Injectable()
export class UsuariosService {

  constructor(private http: HttpClient) { }

  // public getPersonagens(): Promise<Usuario[]> {
    
  //   return this.http.get(`${URL_API}/personagens`)
  //     .toPromise()
  //     .then((resposta: any) => resposta)
  // }
}