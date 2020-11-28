import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule, ExtraOptions } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { MainComponent } from './main/main.component';
import { LoginComponent } from './login/login.component';
import {PasswordRecoveryComponent} from './password-recovery/password-recovery.component';
import { RegisterComponent } from './register/register.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { CongratulationsComponent } from './congratulations/congratulations.component';
import { ContactComponent } from './contact/contact.component';
import {MestreComponent} from './mestre/mestre.component';
import {PersonagemComponent} from './personagem/personagem.component';
import {ConviteComponent} from './convite/convite.component';
import {RegisterCharacterComponent} from './register-character/register-character.component';
import {RegisterMasterComponent} from './register-master/register-master.component';
import {FeedComponent} from './feed/feed.component';
import {ConvidarComponent} from './convidar/convidar.component';
import {DashboardUserComponent} from './dashboard-user/dashboard-user.component';

const APP_ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'main', component:MainComponent},
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'password-recovery', component: PasswordRecoveryComponent},
    { path: 'confirmation', component: ConfirmationComponent },
    { path: 'congratulations', component: CongratulationsComponent},
    { path: 'contact', component: ContactComponent},
    { path: 'mestre', component: MestreComponent},
    { path: 'personagem', component: PersonagemComponent},
    { path: 'convite', component: ConviteComponent},
    { path: 'register-character', component: RegisterCharacterComponent},
    { path: 'register-master', component: RegisterMasterComponent},
    { path: 'feed', component: FeedComponent},
    { path: 'convidar', component:ConvidarComponent},
    { path: 'dashboard-user', component:DashboardUserComponent},
];

const config: ExtraOptions = {
    useHash: true
};

@NgModule({
    imports: [RouterModule.forRoot(APP_ROUTES, config)],
    exports: [RouterModule]
})

export class AppRoutingModule {}
