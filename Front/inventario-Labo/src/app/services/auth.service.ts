import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

interface LoginResponse {
  message: string;
  token: string;
  role: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/api/v1/login';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) { 
    this.checkAuthenticationStatus();
  }

  login(email: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.loginUrl, { email, password })
      .pipe(
        tap(response => {
          if (response && response.token) {
            sessionStorage.setItem('authToken', response.token);
            sessionStorage.setItem('userRole', response.role);
            sessionStorage.setItem('userEmail', response.email);
            this.isAuthenticatedSubject.next(true);
          }
        }),
        catchError(this.handleError)
      );
  }

  logout(): void {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('userRole');
    sessionStorage.removeItem('userEmail');
    this.isAuthenticatedSubject.next(false);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return sessionStorage.getItem('authToken');
  }

  getRole(): string | null {
    return sessionStorage.getItem('userRole');
  }

  getEmail(): string | null {
    return sessionStorage.getItem('userEmail');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  private checkAuthenticationStatus(): void {
    const token = this.getToken();
    this.isAuthenticatedSubject.next(!!token);
  }

  private handleError(error: any) {
    let errorMessage = 'Error de conexión';
    
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = error.error?.message || error.statusText;
    }
    
    return throwError(() => new Error(errorMessage));
  }
}