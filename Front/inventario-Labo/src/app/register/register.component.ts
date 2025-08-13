import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../components/header/header.component';
import { RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  tipoSeleccionado: 'ALUMNO' | 'PROFESOR' | null = null;

  alumno = {
    nombre: '',
    apellido: '',
    email: '',
    password: '',
    role: 'ALUMNO',
    dni: null
  };

  profesor = {
    nombre: '',
    apellido: '',
    email: '',
    password: '',
    role: 'PROFESOR',
    materiaDada: '',
    carrera: '',
  };

  mostrarCampoCarreraOtro = false;
  carreraOtra = '';
  claveSecreta = '';
  claveIncorrecta = false;

  loading = false;
  errorMessage = '';
  successMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  seleccionarTipo(tipo: 'ALUMNO' | 'PROFESOR') {
    this.tipoSeleccionado = tipo;
    this.resetFormularios();
  }

  reiniciarSeleccion() {
    this.tipoSeleccionado = null;
    this.resetFormularios();
    this.errorMessage = '';
    this.successMessage = '';
  }

  resetFormularios() {
    this.alumno = {
      nombre: '',
      apellido: '',
      email: '',
      password: '',
      role: 'ALUMNO',
      dni: null
    };
    this.profesor = {
      nombre: '',
      apellido: '',
      email: '',
      password: '',
      role: 'PROFESOR',
      materiaDada: '',
      carrera: ''
    };
    this.mostrarCampoCarreraOtro = false;
    this.carreraOtra = '';
    this.claveSecreta = '';
    this.claveIncorrecta = false;
  }

  onSubmitAlumno() {
    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.http.post('http://localhost:8080/api/v1/registration', this.alumno)
      .subscribe({
        next: () => {
          this.successMessage = '¡Te registraste con éxito! Serás redirigido al login...';
          this.loading = false;
          this.resetFormularios();
          setTimeout(() => this.router.navigate(['/login']), 2000);
        },
        error: (error) => {
          this.loading = false;
          if (error.status === 400 || error.status === 409) {
            this.errorMessage = 'Este email ya está registrado.';
          } else {
            this.errorMessage = 'Ocurrió un error al registrarte.';
          }
        }
      });
  }

  onSubmitProfesor() {
    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';
    this.claveIncorrecta = false;

    if (this.claveSecreta !== 'ProfeDeLabo') {
      this.claveIncorrecta = true;
      this.loading = false;
      return;
    }

    if (this.mostrarCampoCarreraOtro && this.carreraOtra) {
      this.profesor.carrera = this.carreraOtra;
    }

    this.http.post('http://localhost:8080/api/v1/registration', this.profesor)
      .subscribe({
        next: () => {
          this.successMessage = '¡Te registraste con éxito! Serás redirigido al login...';
          this.loading = false;
          this.resetFormularios();
          setTimeout(() => this.router.navigate(['/login']), 2000);
        },
        error: (error) => {
          this.loading = false;
          if (error.status === 400 || error.status === 409) {
            this.errorMessage = 'Este email ya está registrado.';
          } else {
            this.errorMessage = 'Ocurrió un error al registrarte.';
          }
        }
      });
  }

  onCarreraChange(event: any) {
    this.mostrarCampoCarreraOtro = event.target.value === 'Otro';
    if (!this.mostrarCampoCarreraOtro) {
      this.carreraOtra = '';
    }
  }
}
