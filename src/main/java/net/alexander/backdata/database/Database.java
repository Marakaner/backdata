package net.alexander.backdata.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Database {

    private Map<String, Table> tables = new HashMap<>();

    public Table getTable(String name) {
        return this.tables.containsKey(name) ? this.tables.get(name) : null;
    }



}
