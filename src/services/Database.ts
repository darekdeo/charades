import * as SQLite from 'expo-sqlite';
import { Category, Charade } from '../models';

const DB_NAME = 'charades.db';

export const getDBConnection = async () => {
  try {
    return await SQLite.openDatabaseAsync(DB_NAME);
  } catch (e) {
    console.error("Error opening database:", e);
    throw e;
  }
};

export const initDatabase = async () => {
  console.log("[DB] Opening database...");
  const db = await getDBConnection();
  console.log("[DB] Database opened. Creating tables...");
  await db.execAsync(`
    CREATE TABLE IF NOT EXISTS categories (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT NOT NULL,
      description TEXT
    );
    CREATE TABLE IF NOT EXISTS charades (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT NOT NULL,
      category_id INTEGER,
      FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
    );
  `);
  console.log("[DB] Tables created. Seeding...");
  
  try {
    await seedDatabase(db);
    console.log("[DB] Seeding complete.");
  } catch (error) {
    console.warn("Error seeding database:", error);
  }
  return db;
};

const seedDatabase = async (db: SQLite.SQLiteDatabase) => {
  const result: any[] = await db.getAllAsync('SELECT count(*) as count FROM categories');
  if (result.length > 0 && result[0].count > 0) return;

  await db.execAsync(`
    INSERT INTO categories (name, description) VALUES ('Movies', 'Popular movies');
    INSERT INTO categories (name, description) VALUES ('Animals', 'All kinds of animals');
    INSERT INTO categories (name, description) VALUES ('Actions', 'Act it out');
  `);
  
  // Get IDs to insert charades (assuming IDs 1, 2, 3 but safer to just insert blind if auto-inc matches, 
  // but let's just insert some generic ones assuming standard order or sub-queries if supported)
  // SQLite supports sub-queries in INSERT.
  
  await db.execAsync(`
    INSERT INTO charades (name, category_id) VALUES ('The Godfather', (SELECT id FROM categories WHERE name = 'Movies'));
    INSERT INTO charades (name, category_id) VALUES ('Titanic', (SELECT id FROM categories WHERE name = 'Movies'));
    INSERT INTO charades (name, category_id) VALUES ('Lion', (SELECT id FROM categories WHERE name = 'Animals'));
    INSERT INTO charades (name, category_id) VALUES ('Elephant', (SELECT id FROM categories WHERE name = 'Animals'));
    INSERT INTO charades (name, category_id) VALUES ('Dancing', (SELECT id FROM categories WHERE name = 'Actions'));
    INSERT INTO charades (name, category_id) VALUES ('Swimming', (SELECT id FROM categories WHERE name = 'Actions'));
  `);
};

export const getCategories = async (db: SQLite.SQLiteDatabase): Promise<Category[]> => {
  const result = await db.getAllAsync<Category>('SELECT * FROM categories ORDER BY id DESC');
  return result;
};

export const getCharadesByCategory = async (db: SQLite.SQLiteDatabase, categoryId: number): Promise<Charade[]> => {
  const result = await db.getAllAsync<Charade>('SELECT * FROM charades WHERE category_id = ?', [categoryId]);
  return result;
};

export const addCategory = async (db: SQLite.SQLiteDatabase, name: string, description: string) => {
  await db.runAsync('INSERT INTO categories (name, description) VALUES (?, ?)', [name, description]);
};

export const updateCategory = async (db: SQLite.SQLiteDatabase, id: number, name: string, description: string) => {
  await db.runAsync('UPDATE categories SET name = ?, description = ? WHERE id = ?', [name, description, id]);
};

export const deleteCategory = async (db: SQLite.SQLiteDatabase, id: number) => {
  await db.runAsync('DELETE FROM categories WHERE id = ?', [id]);
};

export const addCharade = async (db: SQLite.SQLiteDatabase, name: string, categoryId: number) => {
  await db.runAsync('INSERT INTO charades (name, category_id) VALUES (?, ?)', [name, categoryId]);
};

export const deleteCharade = async (db: SQLite.SQLiteDatabase, id: number) => {
  await db.runAsync('DELETE FROM charades WHERE id = ?', [id]);
};
