import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import { GameSetupScreen } from '../GameSetupScreen';
import { NavigationContainer } from '@react-navigation/native';
import { PaperProvider } from 'react-native-paper';

const mockNavigate = jest.fn();

jest.mock('@react-navigation/native', () => {
  const actualNav = jest.requireActual('@react-navigation/native');
  return {
    ...actualNav,
    useNavigation: () => ({
      navigate: mockNavigate,
    }),
    useRoute: () => ({
      params: { 
        category: { id: 1, name: 'Test Category', description: 'Desc' } 
      },
    }),
  };
});

describe('GameSetupScreen', () => {
  it('renders correctly', () => {
    const { getByText } = render(
      <PaperProvider>
        <NavigationContainer>
          <GameSetupScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    expect(getByText('Play Test Category')).toBeTruthy();
    expect(getByText('Start Game')).toBeTruthy();
  });

  it('navigates to Game screen on start', () => {
    const { getByText } = render(
      <PaperProvider>
        <NavigationContainer>
          <GameSetupScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    fireEvent.press(getByText('Start Game'));
    expect(mockNavigate).toHaveBeenCalledWith('Game', expect.objectContaining({
      category: expect.objectContaining({ name: 'Test Category' }),
      duration: 60
    }));
  });
});
