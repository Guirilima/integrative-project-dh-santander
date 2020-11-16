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
import { RegisterComponent } from './register/register.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { MestreComponent } from './mestre/mestre.component';
import { PersonagemComponent } from './personagem/personagem.component';
import {TopoHomeComponent} from './topo-home/topo-home.component';
import {RodapeComponent} from './rodape/rodape.component';
import {TopoComponent} from './main/topo/topo.component';
import { ConviteComponent } from './convite/convite.component';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;


@NgModule({
	declarations: [
		AppComponent,
		ContactComponent,
		RegisterComponent,
		LoginComponent,
		MainComponent,
		TopoHomeComponent,
		MestreComponent,
		PersonagemComponent,
		TopoComponent,
		RodapeComponent,
		ConviteComponent
	],
	imports: [
		NgxMaskModule.forRoot(),
		BrowserModule,
		BrowserAnimationsModule,
		AppRoutingModule,
		HttpClientModule,
		ReactiveFormsModule,
		FormsModule,
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
