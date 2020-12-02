import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Convite } from '../shared/convite.model';
import {URL_API} from '../app.api';

@Injectable()
export class ConvitesService {

  constructor(private http: HttpClient) { }

  public getConvites(): Promise<Convite[]> {
    console.log("Passei pelo service");
   return null;

  }
}