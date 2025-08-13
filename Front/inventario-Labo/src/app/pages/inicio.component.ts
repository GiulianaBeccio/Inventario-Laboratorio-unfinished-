import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarruselComponent } from '../components/carrusel/carrusel.component';

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
export class InicioComponent { }
