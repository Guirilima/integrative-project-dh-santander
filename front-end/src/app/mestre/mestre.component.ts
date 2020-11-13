import { Component, OnInit } from '@angular/core';
import {MestresService} from '../mestres.service';
import { Mestre } from '../shared/mestre.model';

@Component({
  selector: 'app-mestre',
  templateUrl: './mestre.component.html',
  styleUrls: ['./mestre.component.css'],
  providers: [ MestresService]
})
export class MestreComponent implements OnInit {

  public mestres: Mestre[];

  constructor( private mestresService: MestresService) { }

  ngOnInit(): void {
    this.mestresService.getMestres()
      .then((mestres: Mestre[]) => {
        this.mestres = mestres;
      })
      .catch((param: any) => {
        console.log(param)
      })
  }

}
