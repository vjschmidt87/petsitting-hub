import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetProfile } from '@shared/models/pet.models';
import { environment } from '@env/environment';

@Injectable({ providedIn: 'root' })
export class PetService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/pets`;

  getMyPets(): Observable<PetProfile[]> {
    return this.http.get<PetProfile[]>(this.baseUrl);
  }

  getPetById(id: number): Observable<PetProfile> {
    return this.http.get<PetProfile>(`${this.baseUrl}/${id}`);
  }

  createPet(pet: Partial<PetProfile>): Observable<PetProfile> {
    return this.http.post<PetProfile>(this.baseUrl, pet);
  }

  updatePet(id: number, pet: Partial<PetProfile>): Observable<PetProfile> {
    return this.http.put<PetProfile>(`${this.baseUrl}/${id}`, pet);
  }

  deletePet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
