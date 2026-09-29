import { Component, Input, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CareTip, ANIMAL_EMOJIS } from '@shared/models/pet.models';
import { TranslationService } from '@core/i18n/translation.service';

@Component({
  selector: 'app-tip-card',
  standalone: true,
  imports: [RouterLink],
  template: `
    <a [routerLink]="['/tips', tip.id]" class="tip-card">
      <div class="tip-header">
        <span class="animal-emoji">{{ getEmoji() }}</span>
        <div class="tip-badges">
          <span class="badge badge--primary">{{ t.t('categories.' + tip.category) }}</span>
          <span class="badge badge--info">{{ t.t('difficulty.' + tip.difficulty) }}</span>
        </div>
      </div>
      <h3 class="tip-title">{{ getTitle() }}</h3>
      <p class="tip-summary">{{ getSummary() }}</p>
      <div class="tip-footer">
        <span class="tip-author">{{ t.t('tips.by') }} {{ tip.authorUsername }}</span>
        <span class="tip-read-more">{{ t.t('tips.readMore') }} &rarr;</span>
      </div>
    </a>
  `,
  styleUrl: './tip-card.component.scss'
})
export class TipCardComponent {
  readonly t = inject(TranslationService);

  @Input({ required: true }) tip!: CareTip;

  getEmoji(): string {
    return ANIMAL_EMOJIS[this.tip.animalType];
  }

  getTitle(): string {
    return this.t.currentLang() === 'pt-br' && this.tip.titlePt ? this.tip.titlePt : this.tip.title;
  }

  getSummary(): string {
    return this.t.currentLang() === 'pt-br' && this.tip.summaryPt ? this.tip.summaryPt : this.tip.summary;
  }
}
