import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HeaderComponent } from '../components/header/header.component';

@Component({
  selector: 'app-activated',
  standalone: true,
  imports: [CommonModule, RouterLink, HeaderComponent],
  templateUrl: './activated.component.html',
  styleUrl: './activated.component.css'
})
export class ActivatedComponent implements OnInit {
  successMessage = '';
  errorMessage = '';

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const estado = params['estado'];
      const mensaje = params['mensaje'];

      if (estado === 'exito') {
        this.successMessage = '¡Tu cuenta fue activada exitosamente!';
      } else if (estado === 'error') {
        const msg = mensaje?.replace(/_/g, ' ') || 'Error desconocido';
        this.errorMessage = msg.charAt(0).toUpperCase() + msg.slice(1);
      }
    });
  }

  irAlLogin(): void {
    this.router.navigate(['/login']);
  }
}

