import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react-native';
import { CategoryFormScreen } from '../CategoryFormScreen';
import { NavigationContainer } from '@react-navigation/native';
import { PaperProvider } from 'react-native-paper';

// Mock database service
jest.mock('../../services/Database', () => ({
  getDBConnection: jest.fn(() => Promise.resolve({})),
  getCharadesByCategory: jest.fn(() => Promise.resolve([])),
  addCategory: jest.fn(() => Promise.resolve()),
  updateCategory: jest.fn(() => Promise.resolve()),
  deleteCategory: jest.fn(() => Promise.resolve()),
  addCharade: jest.fn(() => Promise.resolve()),
  deleteCharade: jest.fn(() => Promise.resolve()),
}));

// Mock navigation
const mockGoBack = jest.fn();
jest.mock('@react-navigation/native', () => {
  const actualNav = jest.requireActual('@react-navigation/native');
  return {
    ...actualNav,
    useNavigation: () => ({
      goBack: mockGoBack,
    }),
    useRoute: () => ({
      params: { category: undefined }, // Default to no category (Add mode)
    }),
  };
});

describe('CategoryFormScreen', () => {
  it('renders correctly in add mode', async () => {
    const { getByText, findByText } = render(
        <PaperProvider>
          <NavigationContainer>
            <CategoryFormScreen />
          </NavigationContainer>
        </PaperProvider>
    );

    await findByText('Save');
    expect(getByText('Save')).toBeTruthy();
  });

  it('updates input fields', async () => {
    const { getAllByTestId, getByDisplayValue } = render(
      <PaperProvider>
        <NavigationContainer>
          <CategoryFormScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    await waitFor(() => {
        const nameInput = getAllByTestId('text-input-flat')[0];
        fireEvent.changeText(nameInput, 'New Category');
        expect(getByDisplayValue('New Category')).toBeTruthy();
    });
  });
});