import { Component, inject, OnInit, DestroyRef } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { catchError, of } from 'rxjs';
import { TranslationService } from '@core/i18n/translation.service';
import { TipService } from '@core/services/tip.service';
import { AnimalCardComponent } from '@shared/components/animal-card/animal-card.component';
import { TipCardComponent } from '@shared/components/tip-card/tip-card.component';
import { CareTip, ANIMAL_TYPES, AnimalType } from '@shared/models/pet.models';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RouterLink, AnimalCardComponent, TipCardComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit {
  readonly t = inject(TranslationService);
  private readonly tipService = inject(TipService);
  private readonly destroyRef = inject(DestroyRef);

  animalTypes = ANIMAL_TYPES;
  featuredTips: CareTip[] = [];
  tipCounts: Record<string, number> = {};
  totalTips = 0;

  ngOnInit(): void {
    this.tipService.getTips().pipe(
      catchError(() => of([])),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(tips => {
      this.totalTips = tips.length;
      this.featuredTips = tips.slice(0, 3);
      this.animalTypes.forEach(type => {
        this.tipCounts[type] = tips.filter(t => t.animalType === type).length;
      });
    });
  }

  getTipCount(type: AnimalType): number {
    return this.tipCounts[type] || 0;
  }
}
