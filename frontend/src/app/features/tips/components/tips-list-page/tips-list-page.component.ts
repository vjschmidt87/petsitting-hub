import { Component, inject, OnInit, DestroyRef } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BehaviorSubject, switchMap, catchError, of, debounceTime, distinctUntilChanged, finalize } from 'rxjs';
import { TranslationService } from '@core/i18n/translation.service';
import { TipService } from '@core/services/tip.service';
import { TipCardComponent } from '@shared/components/tip-card/tip-card.component';
import {
  CareTip, AnimalType, CareTipCategory, Difficulty, TipFilters,
  ANIMAL_TYPES, CARE_TIP_CATEGORIES, DIFFICULTIES, ANIMAL_EMOJIS
} from '@shared/models/pet.models';

@Component({
  selector: 'app-tips-list-page',
  standalone: true,
  imports: [FormsModule, TipCardComponent],
  templateUrl: './tips-list-page.component.html',
  styleUrl: './tips-list-page.component.scss'
})
export class TipsListPageComponent implements OnInit {
  readonly t = inject(TranslationService);
  private readonly tipService = inject(TipService);
  private readonly route = inject(ActivatedRoute);
  private readonly destroyRef = inject(DestroyRef);

  animalTypes = ANIMAL_TYPES;
  categories = CARE_TIP_CATEGORIES;
  difficulties = DIFFICULTIES;
  animalEmojis = ANIMAL_EMOJIS;

  tips: CareTip[] = [];
  loading = true;
  searchTerm = '';

  selectedAnimalType: AnimalType | null = null;
  selectedCategory: CareTipCategory | null = null;
  selectedDifficulty: Difficulty | null = null;

  private readonly filter$ = new BehaviorSubject<TipFilters>({});
  private readonly search$ = new BehaviorSubject<string>('');

  ngOnInit(): void {
    this.route.queryParams.pipe(
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(params => {
      if (params['type']) {
        this.selectedAnimalType = params['type'] as AnimalType;
      }
      this.applyFilters();
    });

    this.search$.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(() => this.applyFilters());

    this.filter$.pipe(
      switchMap(filters => {
        this.loading = true;
        return this.tipService.getTips(filters).pipe(
          catchError(() => of([])),
          finalize(() => this.loading = false)
        );
      }),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(tips => this.tips = tips);
  }

  onSearchChange(): void {
    this.search$.next(this.searchTerm);
  }

  selectAnimalType(type: AnimalType | null): void {
    this.selectedAnimalType = type;
    this.applyFilters();
  }

  applyFilters(): void {
    const filters: TipFilters = {};
    if (this.selectedAnimalType) filters.animalType = this.selectedAnimalType;
    if (this.selectedCategory) filters.category = this.selectedCategory;
    if (this.selectedDifficulty) filters.difficulty = this.selectedDifficulty;
    if (this.searchTerm.trim()) filters.search = this.searchTerm.trim();
    this.filter$.next(filters);
  }

  clearFilters(): void {
    this.selectedAnimalType = null;
    this.selectedCategory = null;
    this.selectedDifficulty = null;
    this.searchTerm = '';
    this.applyFilters();
  }

  hasActiveFilters(): boolean {
    return !!(this.selectedAnimalType || this.selectedCategory || this.selectedDifficulty || this.searchTerm.trim());
  }
}
