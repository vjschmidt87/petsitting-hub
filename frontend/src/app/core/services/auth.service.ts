import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { AuthRequest, AuthResponse, RegisterRequest, User } from '@shared/models/pet.models';
import { environment } from '@env/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly userSubject = new BehaviorSubject<User | null>(this.loadUser());
  readonly user$ = this.userSubject.asObservable();

  get isAuthenticated(): boolean {
    return !!this.getToken();
  }

  get currentUser(): User | null {
    return this.userSubject.value;
  }

  login(request: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${environment.apiUrl}/auth/login`, request).pipe(
      tap(res => this.setSession(res))
    );
  }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${environment.apiUrl}/auth/register`, request).pipe(
      tap(res => this.setSession(res))
    );
  }

  logout(): void {
    localStorage.removeItem('petsitting-token');
    localStorage.removeItem('petsitting-user');
    this.userSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem('petsitting-token');
  }

  private setSession(response: AuthResponse): void {
    localStorage.setItem('petsitting-token', response.token);
    const user: User = { username: response.username };
    localStorage.setItem('petsitting-user', JSON.stringify(user));
    this.userSubject.next(user);
  }

  private loadUser(): User | null {
    const stored = localStorage.getItem('petsitting-user');
    return stored ? JSON.parse(stored) : null;
  }
}
