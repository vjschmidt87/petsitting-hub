import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CareTip, TipFilters } from '@shared/models/pet.models';
import { environment } from '@env/environment';

@Injectable({ providedIn: 'root' })
export class TipService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/tips`;

  getTips(filters?: TipFilters): Observable<CareTip[]> {
    let params = new HttpParams();
    if (filters?.animalType) params = params.set('animalType', filters.animalType);
    if (filters?.category) params = params.set('category', filters.category);
    if (filters?.difficulty) params = params.set('difficulty', filters.difficulty);
    if (filters?.search) params = params.set('search', filters.search);
    return this.http.get<CareTip[]>(this.baseUrl, { params });
  }

  getTipById(id: number): Observable<CareTip> {
    return this.http.get<CareTip>(`${this.baseUrl}/${id}`);
  }

  createTip(tip: Partial<CareTip>): Observable<CareTip> {
    return this.http.post<CareTip>(this.baseUrl, tip);
  }

  updateTip(id: number, tip: Partial<CareTip>): Observable<CareTip> {
    return this.http.put<CareTip>(`${this.baseUrl}/${id}`, tip);
  }

  deleteTip(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
