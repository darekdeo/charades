import React from 'react';
import { View, StyleSheet, FlatList } from 'react-native';
import { Text, Button, List, Divider } from 'react-native-paper';
import { useNavigation, useRoute, RouteProp } from '@react-navigation/native';
import { RootStackParamList } from '../navigation/AppNavigator';

type ResultsRouteProp = RouteProp<RootStackParamList, 'Results'>;

export const ResultsScreen = () => {
  const navigation = useNavigation<any>();
  const route = useRoute<ResultsRouteProp>();
  const { score, results } = route.params;

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text variant="displayMedium">Score: {score}</Text>
      </View>
      <FlatList
        data={results}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <List.Item
            title={item.word}
            left={props => <List.Icon {...props} icon={item.correct ? 'check' : 'close'} color={item.correct ? 'green' : 'red'} />}
          />
        )}
        ItemSeparatorComponent={Divider}
      />
      <View style={styles.footer}>
        <Button mode="contained" onPress={() => navigation.navigate('Home')} style={styles.button}>Back to Home</Button>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1 },
  header: { padding: 32, alignItems: 'center', backgroundColor: '#f0f0f0' },
  footer: { padding: 16 },
  button: { width: '100%' }
});
