import React from 'react';
import { render, waitFor } from '@testing-library/react-native';
import { HomeScreen } from '../HomeScreen';
import { NavigationContainer } from '@react-navigation/native';
import { PaperProvider } from 'react-native-paper';

// Mock database service
jest.mock('../../services/Database', () => ({
  initDatabase: jest.fn(() => Promise.resolve({})),
  getCategories: jest.fn(() => Promise.resolve([
    { id: 1, name: 'Test Category', description: 'Test Description' }
  ])),
}));

describe('HomeScreen', () => {
  it('renders correctly', async () => {
    const { getByText } = render(
      <PaperProvider>
        <NavigationContainer>
          <HomeScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    await waitFor(() => {
      expect(getByText('Test Category')).toBeTruthy();
      expect(getByText('Test Description')).toBeTruthy();
    });
  });
});
