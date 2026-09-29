import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TranslationService } from '@core/i18n/translation.service';

@Component({
  selector: 'app-contact-page',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './contact-page.component.html',
  styleUrl: './contact-page.component.scss'
})
export class ContactPageComponent {
  readonly t = inject(TranslationService);

  name = '';
  email = '';
  subject = '';
  message = '';
  submitted = false;

  onSubmit(): void {
    if (!this.name || !this.email || !this.subject || !this.message) return;

    const mailto = `mailto:contact@petsittinghub.com?subject=${encodeURIComponent(this.subject)}&body=${encodeURIComponent(
      `Name: ${this.name}\nEmail: ${this.email}\n\n${this.message}`
    )}`;
    window.open(mailto, '_blank');
    this.submitted = true;
  }

  reset(): void {
    this.name = '';
    this.email = '';
    this.subject = '';
    this.message = '';
    this.submitted = false;
  }
}
