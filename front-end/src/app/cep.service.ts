import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private http: HttpClient) { }

  public getCep(apiURL,cep,produto): any{

    return this.http.post(`${ apiURL }/endereco/buscar-pelo-cep/${ cep }` , produto).toPromise()
    
  }
}
