import React, { useState, useEffect } from 'react';
import { View, StyleSheet, FlatList, Alert } from 'react-native';
import { TextInput, Button, List, IconButton } from 'react-native-paper';
import { useNavigation, useRoute, RouteProp } from '@react-navigation/native';
import { RootStackParamList } from '../navigation/AppNavigator';
import { getDBConnection, addCategory, updateCategory, deleteCategory, getCharadesByCategory, addCharade, deleteCharade } from '../services/Database';
import { Charade } from '../models';

type CategoryFormRouteProp = RouteProp<RootStackParamList, 'CategoryForm'>;

export const CategoryFormScreen = () => {
  const navigation = useNavigation();
  const route = useRoute<CategoryFormRouteProp>();
  const category = route.params?.category;

  const [name, setName] = useState(category?.name || '');
  const [description, setDescription] = useState(category?.description || '');
  const [charades, setCharades] = useState<Charade[]>([]);
  const [newCharade, setNewCharade] = useState('');
  const [db, setDb] = useState<any>(null);

  useEffect(() => {
    const init = async () => {
      const database = await getDBConnection();
      setDb(database);
      if (category) {
        loadCharades(database);
      }
    };
    init();
  }, []);

  const loadCharades = async (database: any) => {
    if (!category) return;
    const data = await getCharadesByCategory(database, category.id);
    setCharades(data);
  };

  const handleSave = async () => {
    if (!name) return;
    if (category) {
      await updateCategory(db, category.id, name, description);
    } else {
      await addCategory(db, name, description);
    }
    navigation.goBack();
  };

  const handleDelete = async () => {
    if (!category) return;
    Alert.alert('Delete Category', 'Are you sure?', [
      { text: 'Cancel', style: 'cancel' },
      { text: 'Delete', style: 'destructive', onPress: async () => {
          await deleteCategory(db, category.id);
          navigation.goBack();
        } 
      }
    ]);
  };

  const handleAddCharade = async () => {
    if (!newCharade || !category) return; // Must save category first? Ideally yes.
    // If category is new, we can't add charades yet.
    if (!category) {
      Alert.alert('Save First', 'Please save the category before adding words.');
      return;
    }
    await addCharade(db, newCharade, category.id);
    setNewCharade('');
    loadCharades(db);
  };

  const handleDeleteCharade = async (id: number) => {
    await deleteCharade(db, id);
    loadCharades(db);
  };

  return (
    <View style={styles.container}>
      <TextInput label="Category Name" value={name} onChangeText={setName} style={styles.input} />
      <TextInput label="Description" value={description} onChangeText={setDescription} style={styles.input} />
      
      <View style={styles.row}>
        <Button mode="contained" onPress={handleSave} style={styles.button}>Save</Button>
        {category && <Button mode="outlined" onPress={handleDelete} color="red" style={styles.button}>Delete</Button>}
      </View>

      {category && (
        <>
          <View style={styles.addRow}>
            <TextInput 
              label="Add Word" 
              value={newCharade} 
              onChangeText={setNewCharade} 
              style={{ flex: 1 }} 
            />
            <IconButton icon="plus" onPress={handleAddCharade} />
          </View>
          <FlatList
            data={charades}
            keyExtractor={item => item.id.toString()}
            renderItem={({ item }) => (
              <List.Item
                title={item.name}
                right={props => <IconButton icon="delete" onPress={() => handleDeleteCharade(item.id)} />}
              />
            )}
          />
        </>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16 },
  input: { marginBottom: 12 },
  row: { flexDirection: 'row', justifyContent: 'space-around', marginBottom: 16 },
  button: { flex: 1, marginHorizontal: 8 },
  addRow: { flexDirection: 'row', alignItems: 'center', marginBottom: 8 }
});
