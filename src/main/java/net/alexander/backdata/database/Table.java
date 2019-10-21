package net.alexander.backdata.database;

import lombok.Getter;
import net.alexander.backdata.database.entries.LongEntry;
import net.alexander.backdata.database.entries.StringEntry;

import java.util.List;

public class Table {

    @Getter private String name;
    @Getter private List<DataSet> dataSets;

    public Table(String name) {
        this.name = name;
    }

    public Entry getDataSet(String searchingKey, String givenKey, String givenValue) {
        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(key.equals(searchingKey)) {
                    if(dataSet.getEntry(givenKey) != null) {
                        if(dataSet.getEntry(givenKey) instanceof StringEntry) {
                            StringEntry entry = (StringEntry) dataSet.getEntry(givenKey);
                            if (entry.getString().equals(givenValue)) {
                                return dataSet.getEntry(searchingKey);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public Entry getDataSet(String searchingKey, String givenKey, double givenValue) {
        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(key.equals(searchingKey)) {
                    if(dataSet.getEntry(givenKey) != null) {
                        if(dataSet.getEntry(givenKey) instanceof LongEntry) {
                            LongEntry entry = (LongEntry) dataSet.getEntry(givenKey);
                            if (entry.getNumber().doubleValue() == givenValue) {
                                return dataSet.getEntry(searchingKey);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}
