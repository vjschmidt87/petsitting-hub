import { Component, inject } from '@angular/core';
import { TranslationService } from '@core/i18n/translation.service';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer class="footer">
      <div class="footer-inner">
        <span class="copyright">{{ t.t('footer.copyright') }}</span>
        <nav class="footer-links">
          <a href="#">{{ t.t('footer.about') }}</a>
          <a href="#">{{ t.t('footer.privacy') }}</a>
          <a href="#">{{ t.t('footer.terms') }}</a>
        </nav>
      </div>
    </footer>
  `,
  styleUrl: './footer.component.scss'
})
export class FooterComponent {
  readonly t = inject(TranslationService);
}
