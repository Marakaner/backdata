package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.INumberEntry;

public class FloatEntry implements INumberEntry {

    @Getter
    @Setter
    private Float value;

    public FloatEntry(Float value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.FLOAT;
    }

    @Override
    public Object getNumberValue() {
        return this.value;
    }
}
