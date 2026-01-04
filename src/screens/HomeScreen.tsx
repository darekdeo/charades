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
  const [error, setError] = useState<string | null>(null);
  const [db, setDb] = useState<SQLiteDatabase | null>(null);
  const isInit = React.useRef(false);

  const initialize = React.useCallback(async () => {
    if (isInit.current) return;
    isInit.current = true;
    setLoading(true);
    setError(null);

    try {
      console.log("Starting DB init...");
      const timeoutPromise = new Promise<never>((_, reject) => 
        setTimeout(() => reject(new Error("Database initialization timed out")), 15000)
      );
      const database = await Promise.race([initDatabase(), timeoutPromise]);
      console.log("DB init success");
      setDb(database);
      await loadCategories(database);
    } catch (err) {
      console.error("DB Init Error:", err);
      setError("Failed to initialize database. " + (err instanceof Error ? err.message : String(err)));
      // Allow retry
      isInit.current = false; 
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    initialize();
  }, [initialize]);

  const loadCategories = async (database: SQLiteDatabase) => {
    try {
      // setLoading(true); // Don't double set loading if called from init
      const data = await getCategories(database);
      setCategories(data);
    } catch (err) {
      console.error("Load Categories Error:", err);
      setError("Failed to load categories.");
    }
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

  if (error) {
    return (
      <View style={styles.center}>
        <List.Icon icon="alert-circle" color="red" />
        <List.Subheader style={{ color: 'red' }}>{error}</List.Subheader>
        <FAB icon="refresh" onPress={() => { isInit.current = false; initialize(); }} label="Retry" />
      </View>
    );
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
