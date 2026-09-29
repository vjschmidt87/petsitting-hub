import { Routes } from '@angular/router';
import { authGuard } from '@core/guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('@features/home/components/home-page/home-page.component').then(m => m.HomePageComponent)
  },
  {
    path: 'tips',
    loadComponent: () => import('@features/tips/components/tips-list-page/tips-list-page.component').then(m => m.TipsListPageComponent)
  },
  {
    path: 'tips/:id',
    loadComponent: () => import('@features/tips/components/tip-detail-page/tip-detail-page.component').then(m => m.TipDetailPageComponent)
  },
  {
    path: 'my-pets',
    canActivate: [authGuard],
    loadComponent: () => import('@features/pets/components/my-pets-page/my-pets-page.component').then(m => m.MyPetsPageComponent)
  },
  {
    path: 'login',
    loadComponent: () => import('@features/auth/components/login-page/login-page.component').then(m => m.LoginPageComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('@features/auth/components/register-page/register-page.component').then(m => m.RegisterPageComponent)
  },
  {
    path: 'contact',
    loadComponent: () => import('@features/contact/components/contact-page/contact-page.component').then(m => m.ContactPageComponent)
  },
  { path: '**', redirectTo: '' }
];
