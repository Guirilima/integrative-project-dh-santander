import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Convite } from '../shared/convite.model';
import {URL_API} from '../app.api';

@Injectable()
export class ConvitesService {

  constructor(private http: HttpClient) { }

  public getConvites(): Promise<Convite[]> {
    
    return this.http.get(`${URL_API}/convites`)
      .toPromise()
      .then((resposta: any) => resposta)
  }
}