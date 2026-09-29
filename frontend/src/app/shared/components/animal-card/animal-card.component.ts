import { Component, Input, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AnimalType, ANIMAL_EMOJIS } from '@shared/models/pet.models';
import { TranslationService } from '@core/i18n/translation.service';

@Component({
  selector: 'app-animal-card',
  standalone: true,
  imports: [RouterLink],
  template: `
    <a [routerLink]="['/tips']" [queryParams]="{ type: animalType }" class="animal-card">
      <span class="emoji">{{ getEmoji() }}</span>
      <span class="name">{{ t.t('animals.' + animalType) }}</span>
      <span class="count">{{ t.t('animals.tipCount', { count: tipCount }) }}</span>
    </a>
  `,
  styleUrl: './animal-card.component.scss'
})
export class AnimalCardComponent {
  readonly t = inject(TranslationService);

  @Input({ required: true }) animalType!: AnimalType;
  @Input() tipCount = 0;

  getEmoji(): string {
    return ANIMAL_EMOJIS[this.animalType];
  }
}
