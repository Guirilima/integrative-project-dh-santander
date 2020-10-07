import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ContactComponent } from './contact/contact.component';
import { NgxBootstrapIconsModule, allIcons } from 'ngx-bootstrap-icons';


@NgModule({
	declarations: [
		AppComponent,
		ContactComponent
],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		AppRoutingModule,
    NgbModule,
    CommonModule,
    NgxBootstrapIconsModule.pick(allIcons),
  ],
  exports: [
    AppComponent
  ],
	providers: [],
	bootstrap: [AppComponent]
})

export class AppModule { }
