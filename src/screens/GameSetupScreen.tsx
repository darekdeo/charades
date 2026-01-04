import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { Button, Text, SegmentedButtons } from 'react-native-paper';
import { useNavigation, useRoute, RouteProp } from '@react-navigation/native';
import { RootStackParamList } from '../navigation/AppNavigator';

type GameSetupRouteProp = RouteProp<RootStackParamList, 'GameSetup'>;

export const GameSetupScreen = () => {
  const navigation = useNavigation<any>();
  const route = useRoute<GameSetupRouteProp>();
  const { category } = route.params;

  const [duration, setDuration] = useState('60');

  return (
    <View style={styles.container}>
      <Text variant="headlineMedium" style={styles.title}>Play {category.name}</Text>
      <Text style={styles.label}>Round Duration (seconds)</Text>
      <SegmentedButtons
        value={duration}
        onValueChange={setDuration}
        buttons={[
          { value: '60', label: '60s' },
          { value: '90', label: '90s' },
          { value: '120', label: '120s' },
        ]}
        style={styles.segment}
      />
      <Button 
        mode="contained" 
        onPress={() => navigation.navigate('Game', { category, duration: parseInt(duration) })}
        style={styles.button}
      >
        Start Game
      </Button>
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, justifyContent: 'center' },
  title: { textAlign: 'center', marginBottom: 32 },
  label: { marginBottom: 8, textAlign: 'center' },
  segment: { marginBottom: 32 },
  button: { paddingVertical: 8 }
});
