import { Component, OnInit } from '@angular/core';
import { PersonagensService } from '../services/personagens.service';
import { MestresService } from '../services/mestres.service';
import { Personagem } from '../shared/personagem.model';
import { Mestre } from '../shared/mestre.model';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
  providers: [PersonagensService, MestresService]
})
export class MainComponent implements OnInit {

  public personagens: Personagem[];
  public mestres: Mestre[];

  constructor(private personagensService: PersonagensService, private mestresService: MestresService) { }

  ngOnInit() {
    this.personagensService.getPersonagens()
      .then((personagens: Personagem[]) => {
        this.personagens = personagens;
      })
      .catch((param: any) => {
        console.log(param);
      })

    this.mestresService.getMestres()
      .then((mestres: Mestre[]) => {
        this.mestres = mestres;
      })
      .catch((param: any) => {
        console.log(param)
      })
  }

}
