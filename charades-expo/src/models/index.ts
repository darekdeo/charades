export interface Category {
  id: number;
  name: string;
  description: string;
}

export interface Charade {
  id: number;
  name: string;
  category_id: number;
}
