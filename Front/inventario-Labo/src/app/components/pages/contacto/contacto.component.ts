import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-contacto',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent {
  // Información de contacto
  contacto = {
    email: 'laboratorio.robotica.ejemplo@fake.edu',
    telefono: '+54 11 1234-5678',
    direccion: 'Av. de Mayo 866, Cdad. Autónoma de Buenos Aires',
    horario: 'Lunes a Viernes de 8:00 a 18:00'
  };
}