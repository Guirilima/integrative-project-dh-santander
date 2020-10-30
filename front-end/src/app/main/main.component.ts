import { Component, OnInit } from '@angular/core';
import { UsuariosService } from '../usuarios.service';
import { Usuario} from '../shared/usuario.model';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
  providers: [UsuariosService]
})
export class MainComponent implements OnInit {

  public usuarios: Usuario[];

  constructor(private usuariosService: UsuariosService) { }

  ngOnInit() {
    this.usuariosService.getPersonagens()
      .then((personagens: Usuario[]) =>{
        this.usuarios = personagens;
      })
      .catch((param: any)=>{
        console.log(param);
      })
  }

}
