import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  public ImageFairyPath = '/assets/img/fairy-pink.png';

  public ImageWarriorPath =  '/assets/img/warrior-blue.png';

  constructor() { }

  ngOnInit(): void {
  }

}
