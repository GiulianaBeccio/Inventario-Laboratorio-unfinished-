import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarruselComponent } from '../../carrusel/carrusel.component';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [
    CommonModule,
    CarruselComponent
  ],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit{ 

  nombreUsuario: string = '';
  apellidoUsuario: string = '';
  nombreCompleto: string = '';

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.cargarDatosUsuario();
  }

  cargarDatosUsuario(): void {
    this.nombreUsuario = this.authService.getNombre() || '';
    this.apellidoUsuario = this.authService.getApellido() || '';
    this.nombreCompleto = this.authService.getNombreCompleto();
  }

  estaLogueado(): boolean {
    return this.authService.isLoggedIn();
  }



}
