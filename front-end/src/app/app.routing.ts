import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule, ExtraOptions } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import {PasswordRecoveryComponent} from './password-recovery/password-recovery.component';
import { RegisterComponent } from './register/register.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { CongratulationsComponent } from './congratulations/congratulations.component';

const APP_ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'password-recovery', component: PasswordRecoveryComponent},
    { path: 'confirmation', component: ConfirmationComponent },
    { path: 'congratulations', component: CongratulationsComponent },
];

const config: ExtraOptions = {
    useHash: true
};

@NgModule({
    imports: [RouterModule.forRoot(APP_ROUTES, config)],
    exports: [RouterModule]
})

export class AppRoutingModule {}