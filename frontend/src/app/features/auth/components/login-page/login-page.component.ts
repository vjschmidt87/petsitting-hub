import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { TranslationService } from '@core/i18n/translation.service';
import { AuthService } from '@core/services/auth.service';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {
  readonly t = inject(TranslationService);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  username = '';
  password = '';
  errorMessage = '';
  isLoading = false;

  onSubmit(): void {
    if (!this.username || !this.password) {
      this.errorMessage = this.t.t('auth.errors.required');
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = err.error?.message || this.t.t('auth.errors.invalidCredentials');
      }
    });
  }
}
