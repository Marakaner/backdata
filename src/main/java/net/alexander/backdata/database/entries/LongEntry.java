package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class LongEntry implements IEntry {

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
    public Object getGlobalValue() {
        return this.value;
    }
}
