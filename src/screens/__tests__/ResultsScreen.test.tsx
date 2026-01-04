import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import { ResultsScreen } from '../ResultsScreen';
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
        score: 2,
        results: [
          { word: 'Word 1', correct: true },
          { word: 'Word 2', correct: false },
          { word: 'Word 3', correct: true },
        ],
      },
    }),
  };
});

describe('ResultsScreen', () => {
  it('renders score and results correctly', () => {
    const { getByText } = render(
      <PaperProvider>
        <NavigationContainer>
          <ResultsScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    expect(getByText('Score: 2')).toBeTruthy();
    expect(getByText('Word 1')).toBeTruthy();
    expect(getByText('Word 2')).toBeTruthy();
  });

  it('navigates to Home when button is pressed', () => {
    const { getByText } = render(
      <PaperProvider>
        <NavigationContainer>
          <ResultsScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    fireEvent.press(getByText('Back to Home'));
    expect(mockNavigate).toHaveBeenCalledWith('Home');
  });
});
