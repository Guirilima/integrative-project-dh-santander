import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  public postUsuario(usuario, apiURL): any{

    return this.http.post(`${ apiURL }/usuario` , usuario).toPromise();

 }
}
