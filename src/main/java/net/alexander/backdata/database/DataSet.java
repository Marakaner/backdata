package net.alexander.backdata.database;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSet {

    @Getter private Map<String, Entry> entries;

    public DataSet() {
        this.entries = new HashMap<>();
    }

    public Entry getEntry(String key) {
        return entries.getOrDefault(key, null);
    }

    public void setEntry(String key, Entry value) {
        if (this.entries.containsKey(key)) {
            this.entries.replace(key, value);
        } else {
            this.entries.put(key, value);
        }
    }

}
