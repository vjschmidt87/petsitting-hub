import { Component, inject, OnInit, DestroyRef } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';
import { switchMap, catchError, of } from 'rxjs';
import { TranslationService } from '@core/i18n/translation.service';
import { TipService } from '@core/services/tip.service';
import { TipCardComponent } from '@shared/components/tip-card/tip-card.component';
import { CareTip, ANIMAL_EMOJIS } from '@shared/models/pet.models';

@Component({
  selector: 'app-tip-detail-page',
  standalone: true,
  imports: [RouterLink, DatePipe, TipCardComponent],
  templateUrl: './tip-detail-page.component.html',
  styleUrl: './tip-detail-page.component.scss'
})
export class TipDetailPageComponent implements OnInit {
  readonly t = inject(TranslationService);
  private readonly tipService = inject(TipService);
  private readonly route = inject(ActivatedRoute);
  private readonly destroyRef = inject(DestroyRef);

  tip: CareTip | null = null;
  relatedTips: CareTip[] = [];
  loading = true;
  error = '';

  ngOnInit(): void {
    this.route.params.pipe(
      switchMap(params => {
        this.loading = true;
        this.error = '';
        return this.tipService.getTipById(+params['id']).pipe(
          catchError(() => {
            this.error = this.t.t('common.error');
            return of(null);
          })
        );
      }),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(tip => {
      this.tip = tip;
      this.loading = false;
      if (tip) this.loadRelated(tip);
    });
  }

  getTitle(): string {
    if (!this.tip) return '';
    return this.t.currentLang() === 'pt-br' && this.tip.titlePt ? this.tip.titlePt : this.tip.title;
  }

  getContent(): string {
    if (!this.tip) return '';
    return this.t.currentLang() === 'pt-br' && this.tip.contentPt ? this.tip.contentPt : this.tip.content;
  }

  getEmoji(): string {
    return this.tip ? ANIMAL_EMOJIS[this.tip.animalType] : '';
  }

  private loadRelated(tip: CareTip): void {
    this.tipService.getTips({ animalType: tip.animalType }).pipe(
      catchError(() => of([])),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(tips => {
      this.relatedTips = tips.filter(t => t.id !== tip.id).slice(0, 3);
    });
  }
}
