import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ContactComponent } from './contact/contact.component';
import { NgxBootstrapIconsModule, allIcons } from 'ngx-bootstrap-icons';
import { HttpClientModule } from '@angular/common/http';
import { NgxMaskModule, IConfig } from 'ngx-mask'


export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;


@NgModule({
	declarations: [
		AppComponent,
		ContactComponent
],
	imports: [
		NgxMaskModule.forRoot(),
		BrowserModule,
		BrowserAnimationsModule,
		AppRoutingModule,
		HttpClientModule,
    NgbModule,
	CommonModule,
    NgxBootstrapIconsModule.pick(allIcons),
  ],
  exports: [
	AppComponent,

  ],
	providers: [],
	bootstrap: [AppComponent]
})

export class AppModule { }
