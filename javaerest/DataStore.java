package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private static final String DATA_FILE = "data.json";
    private Map<Integer, MyEntity> data;
    private Gson gson;

    public DataStore() {
        gson = new Gson();
        loadData();
    }

    //carica i dati dal file JSON, se il file non esiste inizializza una mappa vuota
    private void loadData() {
        File file = new File(DATA_FILE);
        if(file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<HashMap<Integer, MyEntity>>(){}.getType();
                data = gson.fromJson(reader, type);
            } catch (IOException e) {
                e.printStackTrace();
                data = new HashMap<>();
            }
        } else {
            data = new HashMap<>();
            //inizializzazione dati predefiniti
        }
    }

    //salva i dati su JSON
    private void saveData() {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //restituisce la mappa di entità
    public Map<Integer, MyEntity> getData() {
        return data;
    }

    //aggiunge nuova entità e salva dati
    public void addEntity(MyEntity entity) {
        data.put(entity.getId(), entity);
        saveData();
    }

    //aggiorna entità esistente e salva i dati
    public void updateEntity(MyEntity entity) {
        data.put(entity.getId(), entity);
        saveData();
    }

    //rimuove un'entità in base all'ID e salva i dati
    public void removeEntity(int id) {
        data.remove(id);
        saveData();
    }
}