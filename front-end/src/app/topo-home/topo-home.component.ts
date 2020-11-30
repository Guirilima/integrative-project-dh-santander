import { Component, OnInit } from '@angular/core';
import {LoggedUserService} from '../logged-user.service';
@Component({
  selector: 'app-topo-home',
  templateUrl: './topo-home.component.html',
  styleUrls: ['./topo-home.component.css']
})
export class TopoHomeComponent implements OnInit {

  constructor(private loggedUser: LoggedUserService) { }

  mostrarHomeLogin: boolean;

  ngOnInit(): void {

    if(sessionStorage.getItem('user') === null ) this.mostrarHomeLogin = true;

  }

  sair(){
    sessionStorage.removeItem('user');
  }

}
