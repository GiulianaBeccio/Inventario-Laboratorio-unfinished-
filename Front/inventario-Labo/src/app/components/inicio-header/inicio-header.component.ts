import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inicio-header',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './inicio-header.component.html',
  styleUrl: './inicio-header.component.css'
})
export class InicioHeaderComponent {

}
