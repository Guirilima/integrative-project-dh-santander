import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-convite-card',
  templateUrl: './convite-card.component.html',
  styleUrls: ['./convite-card.component.css']
})
export class ConviteCardComponent implements OnInit {

  @Input() nomeConvite: string;
  @Input() mensagemConvite: string;
  @Input() telefoneCelular: string;

  constructor() { }

  ngOnInit(): void {
  }


  sendWpp()
  {
    window.open(`https://api.whatsapp.com/send?phone=${ this.telefoneCelular }&text=sua%20mensagem`, "_blank");
  }

}
