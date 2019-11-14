package net.alexander.backdata.database;

import lombok.Getter;
import net.alexander.backdata.database.entries.*;

import java.util.ArrayList;
import java.util.List;

public class Table {

    @Getter
    private String name;
    @Getter
    private List<DataSet> dataSets;

    public Table(String name) {
        this.name = name;
        this.dataSets = new ArrayList<>();
    }

    public IEntry getDataSet(String searchingKey, String givenKey, String givenValue) {
        for (DataSet dataSet : dataSets) {
            for (String key : dataSet.getEntries().keySet()) {
                if (key.equals(searchingKey)) {
                    if (dataSet.getEntry(givenKey) != null) {
                        if(String.valueOf(dataSet.getEntry(givenKey)).equals(givenValue)) {
                            return dataSet.getEntry(searchingKey);
                        }
                    }
                }
            }
        }
        return null;
    }

    public void setDataSet(String setKey, IEntry setValue, String givenKey, String givenValue) {
        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(key.equals(givenKey)) {
                    if(dataSet.getEntry(key) != null) {
                        if(dataSet.getEntry(givenKey).getGlobalValue().equals(givenValue)) {
                            dataSet.setEntry(setKey, setValue);
                        }
                    }
                }
            }
        }
    }

    public boolean isDataSetExisting(String searchingKey, String givenKey, String givenValue) {
        return getDataSet(searchingKey, givenKey, givenValue) != null;
    }
}
