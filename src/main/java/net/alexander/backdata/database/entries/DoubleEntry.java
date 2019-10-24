package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.INumberEntry;

public class DoubleEntry implements INumberEntry {

    @Getter
    @Setter
    private Double value;

    public DoubleEntry(Double value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.DOUBLE;
    }

    @Override
    public Object getNumberValue() {
        return this.value;
    }
}
