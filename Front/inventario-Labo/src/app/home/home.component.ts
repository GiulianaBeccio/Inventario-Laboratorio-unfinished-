import { Component } from '@angular/core';
import { HeaderComponent } from "../components/header/header.component";
import { RouterOutlet } from '@angular/router';
import { InicioHeaderComponent } from "../components/inicio-header/inicio-header.component";


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, InicioHeaderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
