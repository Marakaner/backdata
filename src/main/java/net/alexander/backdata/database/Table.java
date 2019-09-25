package net.alexander.backdata.database;

import lombok.Getter;

import java.util.List;

public class Table {

    @Getter private String name;
    @Getter private List<DataSet> dataSets;

    public Table(String name) {
        this.name = name;
    }

    public DataSet getDataSet(String key) {
        for(DataSet dataSet : dataSets) {
            for(String dataKey : dataSet.getEntries().keySet()) {
                if(dataKey.equals(key)) {
                    return dataSet;
                }
            }
        }
        return null;
    }

}
