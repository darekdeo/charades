// src/services/__tests__/Database.test.ts

import { initDatabase, getCategories, addCategory } from '../Database';

const mockDb = {
  execAsync: jest.fn(() => Promise.resolve()),
  runAsync: jest.fn(() => Promise.resolve({ lastInsertRowId: 1, changes: 1 })),
  getAllAsync: jest.fn(() => Promise.resolve([])),
  getFirstAsync: jest.fn(() => Promise.resolve(null)),
};

jest.mock('expo-sqlite', () => ({
  openDatabaseAsync: jest.fn(() => Promise.resolve(mockDb)),
}));

describe('Database Service', () => {
  beforeEach(() => {
    // Clear mocks before each test
    mockDb.execAsync.mockClear();
    mockDb.runAsync.mockClear();
    mockDb.getAllAsync.mockClear();
  });

  it('initDatabase should create tables', async () => {
    // Mock the category count check to ensure seeding happens
    mockDb.getAllAsync.mockResolvedValueOnce([{ count: 0 }]);
    await initDatabase();

    // Check that CREATE TABLE statements were executed
    expect(mockDb.execAsync).toHaveBeenCalledWith(expect.stringContaining('CREATE TABLE IF NOT EXISTS categories'));
    expect(mockDb.execAsync).toHaveBeenCalledWith(expect.stringContaining('CREATE TABLE IF NOT EXISTS charades'));
  });

  it('getCategories should query the categories table', async () => {
    const mockCategories = [{ id: 1, name: 'Movies', description: 'Popular movies' }];
    mockDb.getAllAsync.mockResolvedValueOnce(mockCategories);

    const categories = await getCategories(mockDb as any);

    expect(mockDb.getAllAsync).toHaveBeenCalledWith('SELECT * FROM categories ORDER BY id DESC');
    expect(categories).toEqual(mockCategories);
  });

  it('addCategory should insert a new category', async () => {
    await addCategory(mockDb as any, 'New Category', 'New Desc');

    expect(mockDb.runAsync).toHaveBeenCalledWith(
      'INSERT INTO categories (name, description) VALUES (?, ?)',
      ['New Category', 'New Desc']
    );
  });
});
