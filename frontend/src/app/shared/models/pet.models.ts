export type AnimalType = 'DOG' | 'CAT' | 'BIRD' | 'FISH' | 'LIZARD' | 'EXOTIC';

export type CareTipCategory = 'FEEDING' | 'GROOMING' | 'HEALTH' | 'HOUSING' | 'BEHAVIOR' | 'TRAINING' | 'GENERAL';

export type Difficulty = 'BEGINNER' | 'INTERMEDIATE' | 'ADVANCED';

export interface CareTip {
  id: number;
  title: string;
  titlePt: string;
  content: string;
  contentPt: string;
  summary: string;
  summaryPt: string;
  animalType: AnimalType;
  category: CareTipCategory;
  difficulty: Difficulty;
  imageUrl?: string;
  authorUsername: string;
  createdAt: string;
  updatedAt: string;
}

export interface PetProfile {
  id: number;
  name: string;
  species: string;
  breed?: string;
  age?: number;
  weight?: number;
  description?: string;
  imageUrl?: string;
  animalType: AnimalType;
  createdAt: string;
}

export interface TipFilters {
  animalType?: AnimalType;
  category?: CareTipCategory;
  difficulty?: Difficulty;
  search?: string;
}

export interface AuthRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  username: string;
}

export interface User {
  username: string;
  email?: string;
}

export const ANIMAL_EMOJIS: Record<AnimalType, string> = {
  DOG: '🐕',
  CAT: '🐱',
  BIRD: '🐦',
  FISH: '🐠',
  LIZARD: '🦎',
  EXOTIC: '🐾'
};

export const ANIMAL_TYPES: AnimalType[] = ['DOG', 'CAT', 'BIRD', 'FISH', 'LIZARD', 'EXOTIC'];

export const CARE_TIP_CATEGORIES: CareTipCategory[] = [
  'FEEDING', 'GROOMING', 'HEALTH', 'HOUSING', 'BEHAVIOR', 'TRAINING', 'GENERAL'
];

export const DIFFICULTIES: Difficulty[] = ['BEGINNER', 'INTERMEDIATE', 'ADVANCED'];
