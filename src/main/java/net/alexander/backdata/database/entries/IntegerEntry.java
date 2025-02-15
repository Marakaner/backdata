package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class IntegerEntry implements IEntry {

    @Getter
    @Setter
    private Integer value;

    public IntegerEntry(int value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.INTEGER;
    }

    @Override
    public Object getGlobalValue() {
        return this.value;
    }
}
