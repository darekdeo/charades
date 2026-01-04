import React from 'react';
import { render, waitFor } from '@testing-library/react-native';
import { act } from 'react-test-renderer';
import { GameScreen } from '../GameScreen';
import { NavigationContainer } from '@react-navigation/native';
import { PaperProvider } from 'react-native-paper';
import { Platform } from 'react-native';

const mockNavigate = jest.fn();
const mockReplace = jest.fn();

jest.mock('@react-navigation/native', () => {
  const actualNav = jest.requireActual('@react-navigation/native');
  return {
    ...actualNav,
    useNavigation: () => ({
      navigate: mockNavigate,
      replace: mockReplace,
    }),
    useRoute: () => ({
      params: { 
        category: { id: 1, name: 'Test Category', description: 'Desc' },
        duration: 60,
      },
    }),
  };
});

describe('GameScreen', () => {
  beforeEach(() => {
    jest.useFakeTimers();
    Platform.OS = 'ios';
  });

  afterEach(() => {
    jest.useRealTimers();
  });

  it('shows countdown initially and transitions to playing state', async () => {
    const { getByText } = render(
      <PaperProvider>
        <NavigationContainer>
          <GameScreen />
        </NavigationContainer>
      </PaperProvider>
    );

    await waitFor(() => expect(getByText('Get Ready!')).toBeTruthy());
    expect(getByText('3')).toBeTruthy();

    // Advance timers by 3 seconds to get past the countdown
    act(() => {
      jest.advanceTimersByTime(3000);
    });

    // Now check if the game has started
    await waitFor(() => {
      expect(getByText('60')).toBeTruthy();
    });
  });
});