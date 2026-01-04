import React from 'react';
import { render } from '@testing-library/react-native';
import App from './App';

// Mock child components or services that might cause issues in a deep render if not already mocked in setup
jest.mock('./src/services/Database', () => ({
  initDatabase: jest.fn(() => Promise.resolve({})),
  getCategories: jest.fn(() => Promise.resolve([])),
}));

describe('App', () => {
  it('renders without crashing', () => {
    render(<App />);
  });
});
