import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { HomeScreen } from '../screens/HomeScreen';
import { CategoryFormScreen } from '../screens/CategoryFormScreen';
import { GameSetupScreen } from '../screens/GameSetupScreen';
import { GameScreen } from '../screens/GameScreen';
import { ResultsScreen } from '../screens/ResultsScreen';
import { Category } from '../models';

export type RootStackParamList = {
  Home: undefined;
  CategoryForm: { category?: Category } | undefined;
  GameSetup: { category: Category };
  Game: { category: Category; duration: number };
  Results: { score: number; results: { word: string; correct: boolean }[] };
};

const Stack = createStackNavigator<RootStackParamList>();

export const AppNavigator = () => {
  return (
    <Stack.Navigator initialRouteName="Home">
      <Stack.Screen name="Home" component={HomeScreen} options={{ title: 'Charades' }} />
      <Stack.Screen name="CategoryForm" component={CategoryFormScreen} options={{ title: 'Manage Category' }} />
      <Stack.Screen name="GameSetup" component={GameSetupScreen} options={{ title: 'Setup Game' }} />
      <Stack.Screen name="Game" component={GameScreen} options={{ headerShown: false }} />
      <Stack.Screen name="Results" component={ResultsScreen} options={{ title: 'Results', headerLeft: () => null }} />
    </Stack.Navigator>
  );
};
