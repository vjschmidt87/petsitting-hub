import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { TranslationService } from '@core/i18n/translation.service';
import { AuthService } from '@core/services/auth.service';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent {
  readonly t = inject(TranslationService);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  username = '';
  email = '';
  password = '';
  confirmPassword = '';
  errorMessage = '';
  isLoading = false;

  onSubmit(): void {
    if (!this.username || !this.email || !this.password || !this.confirmPassword) {
      this.errorMessage = this.t.t('auth.errors.required');
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.errorMessage = this.t.t('auth.errors.passwordMismatch');
      return;
    }

    if (this.password.length < 6) {
      this.errorMessage = this.t.t('auth.errors.passwordMinLength');
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.register({
      username: this.username,
      email: this.email,
      password: this.password
    }).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = err.error?.message || this.t.t('auth.errors.registrationFailed');
      }
    });
  }
}
