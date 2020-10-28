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

const APP_ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'main', component:MainComponent},
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'password-recovery', component: PasswordRecoveryComponent},
    { path: 'confirmation', component: ConfirmationComponent },
    { path: 'congratulations', component: CongratulationsComponent},
    { path: 'contact', component: ContactComponent}
];

const config: ExtraOptions = {
    useHash: true
};

@NgModule({
    imports: [RouterModule.forRoot(APP_ROUTES, config)],
    exports: [RouterModule]
})

export class AppRoutingModule {}
