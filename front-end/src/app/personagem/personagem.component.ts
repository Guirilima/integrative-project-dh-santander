import { Component, OnInit } from '@angular/core';
import { PersonagensService } from '../services/personagens.service';
import { Personagem } from '../shared/personagem.model';


@Component({
  selector: 'app-personagem',
  templateUrl: './personagem.component.html',
  styleUrls: ['./personagem.component.css'],
  providers: [PersonagensService]
})
export class PersonagemComponent implements OnInit {

  public personagens: Personagem[];

  constructor(private personagensService: PersonagensService) { }

  ngOnInit() {
    this.personagensService.getPersonagens()
      .then((personagens: Personagem[]) => {
        this.personagens = personagens;
      })
      .catch((param: any) => {
        console.log(param);
      })
  }


}
