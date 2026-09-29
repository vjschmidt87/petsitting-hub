import { Injectable, signal } from '@angular/core';
import { en } from './en';
import { ptBr } from './pt-br';

export type Lang = 'en' | 'pt-br';

@Injectable({ providedIn: 'root' })
export class TranslationService {
  private lang = signal<Lang>(this.getInitialLang());
  readonly currentLang = this.lang.asReadonly();

  t(key: string, params?: Record<string, string | number>): string {
    const translations = this.lang() === 'en' ? en : ptBr;
    let value: string = key.split('.').reduce((obj: any, k: string) => obj?.[k], translations) ?? key;
    if (params) {
      Object.entries(params).forEach(([k, v]) => {
        value = value.replace(`{{${k}}}`, String(v));
      });
    }
    return value;
  }

  setLang(lang: Lang): void {
    this.lang.set(lang);
    localStorage.setItem('petsitting-lang', lang);
  }

  toggleLang(): void {
    this.setLang(this.lang() === 'en' ? 'pt-br' : 'en');
  }

  private getInitialLang(): Lang {
    return (localStorage.getItem('petsitting-lang') as Lang) || 'en';
  }
}
