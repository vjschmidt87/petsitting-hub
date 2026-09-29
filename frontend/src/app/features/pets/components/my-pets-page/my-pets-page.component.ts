import { Component, inject, OnInit, DestroyRef } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormsModule } from '@angular/forms';
import { BehaviorSubject, switchMap, catchError, of, finalize } from 'rxjs';
import { TranslationService } from '@core/i18n/translation.service';
import { PetService } from '@core/services/pet.service';
import { PetCardComponent } from '@shared/components/pet-card/pet-card.component';
import { PetProfile, AnimalType, ANIMAL_TYPES, ANIMAL_EMOJIS } from '@shared/models/pet.models';

@Component({
  selector: 'app-my-pets-page',
  standalone: true,
  imports: [FormsModule, PetCardComponent],
  templateUrl: './my-pets-page.component.html',
  styleUrl: './my-pets-page.component.scss'
})
export class MyPetsPageComponent implements OnInit {
  readonly t = inject(TranslationService);
  private readonly petService = inject(PetService);
  private readonly destroyRef = inject(DestroyRef);

  pets: PetProfile[] = [];
  loading = true;
  showForm = false;
  editingPet: PetProfile | null = null;
  animalTypes = ANIMAL_TYPES;
  animalEmojis = ANIMAL_EMOJIS;
  errorMessage = '';

  formData = {
    name: '',
    species: '',
    breed: '',
    age: null as number | null,
    weight: null as number | null,
    description: '',
    animalType: 'DOG' as AnimalType
  };

  private readonly load$ = new BehaviorSubject<void>(undefined);

  ngOnInit(): void {
    this.load$.pipe(
      switchMap(() => {
        this.loading = true;
        return this.petService.getMyPets().pipe(
          catchError(() => of([])),
          finalize(() => this.loading = false)
        );
      }),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(pets => this.pets = pets);
  }

  openAddForm(): void {
    this.editingPet = null;
    this.resetForm();
    this.showForm = true;
  }

  openEditForm(pet: PetProfile): void {
    this.editingPet = pet;
    this.formData = {
      name: pet.name,
      species: pet.species,
      breed: pet.breed || '',
      age: pet.age || null,
      weight: pet.weight || null,
      description: pet.description || '',
      animalType: pet.animalType
    };
    this.showForm = true;
  }

  cancelForm(): void {
    this.showForm = false;
    this.editingPet = null;
    this.resetForm();
  }

  savePet(): void {
    if (!this.formData.name || !this.formData.species) return;
    this.errorMessage = '';

    const petData: Partial<PetProfile> = {
      name: this.formData.name,
      species: this.formData.species,
      breed: this.formData.breed || undefined,
      age: this.formData.age || undefined,
      weight: this.formData.weight || undefined,
      description: this.formData.description || undefined,
      animalType: this.formData.animalType
    };

    const request$ = this.editingPet
      ? this.petService.updatePet(this.editingPet.id, petData)
      : this.petService.createPet(petData);

    request$.pipe(
      catchError(err => {
        this.errorMessage = err.error?.message || this.t.t('common.error');
        return of(null);
      }),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(result => {
      if (result) {
        this.showForm = false;
        this.editingPet = null;
        this.resetForm();
        this.load$.next();
      }
    });
  }

  deletePet(id: number): void {
    if (!confirm(this.t.t('pets.confirmDelete'))) return;

    this.petService.deletePet(id).pipe(
      catchError(() => of(null)),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe(() => this.load$.next());
  }

  private resetForm(): void {
    this.formData = {
      name: '',
      species: '',
      breed: '',
      age: null,
      weight: null,
      description: '',
      animalType: 'DOG'
    };
    this.errorMessage = '';
  }
}
