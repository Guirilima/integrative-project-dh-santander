import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Personagem } from './shared/personagem.model';

// import 'rxjs/add/operator/toPromise';


@Injectable()
export class PersonagensService {

  constructor(private http: HttpClient) { }

  public getPersonagens(): Promise<Personagem[]> {
    
    return this.http.get('http://localhost:3000/personagens')
      .toPromise()
      .then((resposta: any) => resposta)
  }
}