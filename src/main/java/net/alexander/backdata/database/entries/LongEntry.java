package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.INumberEntry;

public class LongEntry implements INumberEntry {

    @Getter
    @Setter
    private long value;

    public LongEntry(long value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.LONG;
    }

    @Override
    public Object getNumberValue() {
        return this.value;
    }
}
