package net.alexander.backdata.database;

import lombok.Getter;

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

    /**
     * Getting a Dataset from the table with given arguments
     * @param searchingKey The key your searching for in the dataset
     * @param givenKey The key to identify the Dataset
     * @param givenValue The value to identify the Dataset
     * @return The found Dataset
     */
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

    /**
     * Create a new Entry in thee given Table
     * @param setKey The Key of the entry which is set
     * @param setValue The Value of the entry which is set
     * @param givenKey The Key to identify the Dataset
     * @param givenValue The Value to identify the Dataset
     */
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

    /**
     * Inserting a new DataSet in the table
     * @param setKey The Key of the entry that is set
     * @param setValue The Value of the entry that is set
     */
    public void insertDataSet(String setKey, IEntry setValue) {
        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(!key.equals(setKey)) {
                    DataSet generated = new DataSet();
                    generated.setEntry(setKey, setValue);
                    this.dataSets.add(generated);
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Method to check if a Dataset is existing
     * @param searchingKey The searched key in a Dataset
     * @param givenKey The key to identify a Dataset
     * @param givenValue The value to identify a Dataset
     * @return if the Dataset is existing
     */
    public boolean isDataSetExisting(String searchingKey, String givenKey, String givenValue) {
        return getDataSet(searchingKey, givenKey, givenValue) != null;
    }
}
