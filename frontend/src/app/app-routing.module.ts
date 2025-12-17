// src/app/app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { GerantDashboardComponent } from './components/gerant-dashboard/gerant-dashboard.component';
import { ServeurDashboardComponent } from './components/serveur-dashboard/serveur-dashboard.component';
import { ClientDashboardComponent } from './components/client-dashboard/client-dashboard.component';
import { OrderPageComponent } from './components/order-page/order-page.component';
import {AuthGuard} from "./guards/auth-guard.service";
import {AuthorizationGuard} from "./guards/authorization";
import {Role} from "./enums/role";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    //canActivate: [AuthGuard, AuthorizationGuard],
    data: { role: Role.ADMIN }
  },
  {
    path: 'gerant',
    component: GerantDashboardComponent,
    //canActivate: [AuthGuard, AuthorizationGuard],
    data: { role: Role.GERANT }
  },
  {
    path: 'serveur',
    component: ServeurDashboardComponent,
   // canActivate: [AuthGuard, AuthorizationGuard],
    data: { role: Role.SERVEUR }
  },
  { path: 'client', component: ClientDashboardComponent },
  { path: 'order/:fastfoodId', component: OrderPageComponent },
  { path: '', redirectTo: '/client', pathMatch: 'full' },
  { path: '**', redirectTo: '/client' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
