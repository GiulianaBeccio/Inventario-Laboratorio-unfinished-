import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-carrusel',
  standalone: true,
  imports: [CommonModule, NgFor],
  templateUrl: './carrusel.component.html',
  styleUrls: ['./carrusel.component.css']
})
export class CarruselComponent implements OnInit, OnDestroy {
  imagenes: string[] = [
    'assets/Carrusel/imagen1.jpg',
    'assets/Carrusel/imagen2.jpg',
    'assets/Carrusel/imagen3.jpg'
  ];

  imagenesClonadas: string[] = [];
  currentIndex: number = 1;
  intervalo: any;
  noTransition = false;

  ngOnInit(): void {
    this.imagenesClonadas = [
      this.imagenes[this.imagenes.length - 1],
      this.imagenes[0]
    ];
    this.iniciarAutoSlide();
  }

  ngOnDestroy(): void {
    clearInterval(this.intervalo);
  }

  iniciarAutoSlide(): void {
    this.intervalo = setInterval(() => {
      this.siguiente();
    }, 5000);
  }

  reiniciarIntervalo(): void {
    clearInterval(this.intervalo);
    this.iniciarAutoSlide();
  }

  siguiente(): void {
    this.currentIndex++;
    this.noTransition = false;
    this.reiniciarIntervalo();

    if (this.currentIndex > this.imagenes.length) {
      setTimeout(() => {
        this.noTransition = true;
        this.currentIndex = 1;
      }, 800);
    }
  }

  anterior(): void {
    this.currentIndex--;
    this.noTransition = false;
    this.reiniciarIntervalo();

    if (this.currentIndex < 1) {
      setTimeout(() => {
        this.noTransition = true;
        this.currentIndex = this.imagenes.length;
      }, 800);
    }
  }

  // Ya no se necesita, pero dejo vacio para evitar errores:
  onTransitionEnd(): void {}
}
