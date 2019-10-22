package net.alexander.backdata.database;

import lombok.Getter;
import net.alexander.backdata.database.entries.*;

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

    public Entry getDataSet(String searchingKey, String givenKey, boolean givenValue) {

    }

    public Entry getDataSet(String searchingKey, String givenKey, Entry givenValue) {

        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(key.equals(searchingKey)) {
                    if (dataSet.getEntry(givenKey) != null) {
                        if (givenValue instanceof Long && dataSet.getEntry(givenKey) instanceof LongEntry) {
                            LongEntry entry = (LongEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.longValue()) {

                            }
                        } else if (givenValue instanceof Double && dataSet.getEntry(givenKey) instanceof DoubleEntry) {
                            DoubleEntry entry = (DoubleEntry) dataSet.getEntry(givenKey);

                        } else if (givenValue instanceof Float && dataSet.getEntry(givenKey) instanceof FloatEntry) {

                        } else if (givenValue instanceof Integer && dataSet.getEntry(givenKey) instanceof IntegerEntry) {

                        } else if (givenValue instanceof Byte && dataSet.getEntry(givenKey) instanceof ByteEntry) {

                        } else if (givenValue instanceof Short && dataSet.getEntry(givenKey) instanceof ShortEntry) {

                        }

                        return dataSet.getEntry(searchingKey);
                    }
                }
            }
        }
        return null;
    }

}
