package net.alexander.backdata.database;

import java.util.*;

public class QueryResult {

    List<DataSet> dataSets;

    public QueryResult() {
        this.dataSets = new ArrayList<>();
    }

    public DataSet first() {
        if(!dataSets.isEmpty()) {
            return null;
        }
        return null;
    }

}
