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
                            if (entry.getValue().equals(givenValue)) {
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
        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(key.equals(searchingKey)) {
                    if(dataSet.getEntry(givenKey) != null) {
                        if(dataSet.getEntry(givenKey) instanceof BooleanEntry) {
                            BooleanEntry entry = (BooleanEntry) dataSet.getEntry(givenKey);
                            if(entry.isValue() == givenValue) {
                                return dataSet.getEntry(searchingKey);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public Entry getDataSet(String searchingKey, String givenKey, Number givenValue) {
        for(DataSet dataSet : dataSets) {
            for(String key : dataSet.getEntries().keySet()) {
                if(key.equals(searchingKey)) {
                    if (dataSet.getEntry(givenKey) != null) {
                        if (givenValue instanceof Long && dataSet.getEntry(givenKey) instanceof LongEntry) {
                            LongEntry entry = (LongEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.longValue()) {
                                return dataSet.getEntry(searchingKey);
                            }
                        } else if (givenValue instanceof Double && dataSet.getEntry(givenKey) instanceof DoubleEntry) {
                            DoubleEntry entry = (DoubleEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.doubleValue()) {
                                return dataSet.getEntry(searchingKey);
                            }
                        } else if (givenValue instanceof Float && dataSet.getEntry(givenKey) instanceof FloatEntry) {
                            FloatEntry entry = (FloatEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.floatValue()) {
                                return dataSet.getEntry(searchingKey);
                            }
                        } else if (givenValue instanceof Integer && dataSet.getEntry(givenKey) instanceof IntegerEntry) {
                            IntegerEntry entry = (IntegerEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.intValue()) {
                                return dataSet.getEntry(searchingKey);
                            }
                        } else if (givenValue instanceof Byte && dataSet.getEntry(givenKey) instanceof ByteEntry) {
                            ByteEntry entry = (ByteEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.byteValue()) {
                                return dataSet.getEntry(searchingKey);
                            }
                        } else if (givenValue instanceof Short && dataSet.getEntry(givenKey) instanceof ShortEntry) {
                            ShortEntry entry = (ShortEntry) dataSet.getEntry(givenKey);
                            if(entry.getValue() == givenValue.shortValue()) {
                                return dataSet.getEntry(searchingKey);
                            }
                        }

                        return dataSet.getEntry(searchingKey);
                    }
                }
            }
        }
        return null;
    }

}
