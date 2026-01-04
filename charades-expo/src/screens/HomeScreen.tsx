import React, { useEffect, useState } from 'react';
import { View, FlatList, StyleSheet } from 'react-native';
import { List, FAB, ActivityIndicator } from 'react-native-paper';
import { useNavigation } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import { RootStackParamList } from '../navigation/AppNavigator';
import { Category } from '../models';
import { initDatabase, getCategories } from '../services/Database';
import { SQLiteDatabase } from 'expo-sqlite';

type HomeScreenNavigationProp = StackNavigationProp<RootStackParamList, 'Home'>;

export const HomeScreen = () => {
  const navigation = useNavigation<HomeScreenNavigationProp>();
  const [categories, setCategories] = useState<Category[]>([]);
  const [loading, setLoading] = useState(true);
  const [db, setDb] = useState<SQLiteDatabase | null>(null);

  useEffect(() => {
    const setup = async () => {
      const database = await initDatabase();
      setDb(database);
      loadCategories(database);
    };
    setup();
  }, []);

  const loadCategories = async (database: SQLiteDatabase) => {
    setLoading(true);
    const data = await getCategories(database);
    setCategories(data);
    setLoading(false);
  };

  useEffect(() => {
    const unsubscribe = navigation.addListener('focus', () => {
      if (db) loadCategories(db);
    });
    return unsubscribe;
  }, [navigation, db]);

  if (loading && !db) {
    return <View style={styles.center}><ActivityIndicator size="large" /></View>;
  }

  return (
    <View style={styles.container}>
      <FlatList
        data={categories}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <List.Item
            title={item.name}
            description={item.description}
            left={props => <List.Icon {...props} icon="folder" />}
            right={props => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => navigation.navigate('GameSetup', { category: item })}
            onLongPress={() => navigation.navigate('CategoryForm', { category: item })}
          />
        )}
      />
      <FAB
        style={styles.fab}
        icon="plus"
        onPress={() => navigation.navigate('CategoryForm')}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1 },
  center: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  fab: { position: 'absolute', margin: 16, right: 0, bottom: 0 },
});
