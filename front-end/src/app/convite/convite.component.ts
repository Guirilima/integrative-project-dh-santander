import { Component, OnInit } from '@angular/core';
import {ConvitesService} from '../services/convites.service';
import {Convite} from '../shared/convite.model';

@Component({
  selector: 'app-convite',
  templateUrl: './convite.component.html',
  styleUrls: ['./convite.component.css'],
  providers: [ConvitesService]
})
export class ConviteComponent implements OnInit {

  public convites: Convite[];

  constructor(private convitesService: ConvitesService) { }

  ngOnInit(){
    this.convitesService.getConvites()
      .then((convites: Convite[]) =>{
        this.convites = convites;
      })
      .catch((param: any) => { 
        console.log(param);
      })
  }

}
