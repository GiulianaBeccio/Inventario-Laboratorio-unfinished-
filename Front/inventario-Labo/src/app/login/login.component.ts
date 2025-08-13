import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../components/header/header.component';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  error: string = '';

  constructor(
    private authService: AuthService,
    private router: Router // Inyectamos Router para la redirección
  ) {}

  onSubmit(): void {
    this.error = '';

    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        // Login exitoso: redirigir a la página principal ("/inicio")
        this.router.navigate(['/inicio']);
      },
      error: (err) => {
        if (err.status === 401) {
          this.error = 'Email y/o contraseña incorrectos';
        } else {
          this.error = 'Error del servidor, intente más tarde';
        }
      }
    });
  }
}
