import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Convite } from '../shared/convite.model';

@Injectable()
export class ConvitesService {

  constructor(private http: HttpClient) { }

  public getConvites(): Promise<Convite[]> {
    
    return this.http.get('http://localhost:3000/convites')
      .toPromise()
      .then((resposta: any) => resposta)
  }
}