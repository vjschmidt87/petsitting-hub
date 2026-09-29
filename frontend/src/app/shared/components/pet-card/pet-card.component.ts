import { Component, Input, Output, EventEmitter, inject } from '@angular/core';
import { ANIMAL_EMOJIS, PetProfile } from '@shared/models/pet.models';
import { TranslationService } from '@core/i18n/translation.service';

@Component({
  selector: 'app-pet-card',
  standalone: true,
  template: `
    <div class="pet-card">
      <div class="pet-emoji">{{ getEmoji() }}</div>
      <div class="pet-info">
        <h3 class="pet-name">{{ pet.name }}</h3>
        <p class="pet-species">{{ pet.species }}</p>
        @if (pet.breed) {
          <p class="pet-breed">{{ pet.breed }}</p>
        }
        <div class="pet-meta">
          @if (pet.age) {
            <span>{{ pet.age }} {{ t.t('pets.age') }}</span>
          }
          @if (pet.weight) {
            <span>{{ pet.weight }} {{ t.t('pets.weight') }}</span>
          }
        </div>
      </div>
      <div class="pet-actions">
        <button class="btn btn--outline btn--sm" (click)="edit.emit(pet)">
          {{ t.t('pets.editPet') }}
        </button>
        <button class="btn btn--danger btn--sm" (click)="delete.emit(pet.id)">
          {{ t.t('pets.deletePet') }}
        </button>
      </div>
    </div>
  `,
  styleUrl: './pet-card.component.scss'
})
export class PetCardComponent {
  readonly t = inject(TranslationService);

  @Input({ required: true }) pet!: PetProfile;
  @Output() edit = new EventEmitter<PetProfile>();
  @Output() delete = new EventEmitter<number>();

  getEmoji(): string {
    return ANIMAL_EMOJIS[this.pet.animalType];
  }
}
