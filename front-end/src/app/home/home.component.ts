import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  public showDeck1 = true;
  public showDeck2 = false;
  public showDeck3 = false;
  public teste = true;

  constructor() { }

  ngOnInit(): void {
  }

  onSlideToLeft() {

    if (this.showDeck1){
      this.showDeck1 = !this.showDeck1;
      this.showDeck2 = !this.showDeck2;
    } else if (this.showDeck2){
      this.showDeck2 = !this.showDeck2;
      this.showDeck3 = !this.showDeck3;
    } else {
      this.showDeck3 = !this.showDeck3;
      this.showDeck1 = !this.showDeck1;
    }}

  onSlideToRight() {

    if (this.showDeck1){
      this.showDeck1 = !this.showDeck1;
      this.showDeck3 = !this.showDeck3;
    } else if (this.showDeck2){
      this.showDeck2 = !this.showDeck2;
      this.showDeck1 = !this.showDeck1;
    } else {
      this.showDeck3 = !this.showDeck3;
      this.showDeck2 = !this.showDeck2;
    }}
}





