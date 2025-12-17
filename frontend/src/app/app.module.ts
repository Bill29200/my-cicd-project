// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { GerantDashboardComponent } from './components/gerant-dashboard/gerant-dashboard.component';
import { ServeurDashboardComponent } from './components/serveur-dashboard/serveur-dashboard.component';
import { ClientDashboardComponent } from './components/client-dashboard/client-dashboard.component';
import { OrderPageComponent } from './components/order-page/order-page.component';
import { FilterByFamillePipe, FilterByEtatPipe } from './pipes/filter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminDashboardComponent,
    GerantDashboardComponent,
    ServeurDashboardComponent,
    ClientDashboardComponent,
    OrderPageComponent,
    FilterByFamillePipe,
    FilterByEtatPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
