package net.alexander.backdata.database;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class DataSet {

    @Getter
    private Map<String, IEntry> entries;

    public DataSet() {
        this.entries = new HashMap<>();
    }

    /**
     * Searching for an entry in the Dataset
     * @param key The key of the searched entry
     * @return Entry if found
     */
    public IEntry getEntry(String key) {
        return entries.getOrDefault(key, null);
    }

    /**
     * Set the value of the given key to the given entry
     * @param key The key to identify which value will replaced
     * @param value The Entry for replacing
     */
    public void setEntry(String key, IEntry value) {
        if (this.entries.containsKey(key)) {
            this.entries.replace(key, value);
        } else {
            this.entries.put(key, value);
        }
    }

}
