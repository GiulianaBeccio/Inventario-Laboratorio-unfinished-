import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ActivatedComponent } from './activated/activated.component';
import { InicioComponent } from './components/pages/inicio/inicio.component';
import { ContactoComponent } from './components/pages/contacto/contacto.component';

// Importar el guard de autenticación
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent,
  },
  { 
    path: 'login', 
    component: LoginComponent 
    // ← Pública (sin canActivate)
  },
  { 
    path: 'contact', 
    component: ContactoComponent 
    // ← Pública (sin canActivate)
  },
  { 
    path: 'register', 
    component: RegisterComponent 
    // ← Pública
  },
  { 
    path: 'activado', 
    component: ActivatedComponent 
    // ← Pública (página de activación)
  },
  { 
    path: 'inicio', 
    component: InicioComponent,
    canActivate: [authGuard]  // ← Protegida
  },
  { 
    path: '**', 
    redirectTo: ''  // ← Redirigir a home si la ruta no existe
  }
];