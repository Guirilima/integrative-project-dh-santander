import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Mestre } from '../shared/mestre.model';
import {URL_API} from '../app.api';
// import 'rxjs/add/operator/toPromise';


@Injectable()
export class MestresService {

  constructor(private http: HttpClient) { }

  public getMestres(): Promise<Mestre[]> {
    
    return this.http.get(`${URL_API}/mestres`)
      .toPromise()
      .then((resposta: any) => resposta)
  }
}