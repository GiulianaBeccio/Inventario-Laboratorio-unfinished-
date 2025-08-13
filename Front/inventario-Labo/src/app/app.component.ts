//ng serve  --->  para iniciar el servidor de Angular (Hacerlo en cmd consola y no en PowerShell)

import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { InicioHeaderComponent } from './components/inicio-header/inicio-header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FormsModule, HomeComponent,InicioHeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'inventario-Labo';

}
