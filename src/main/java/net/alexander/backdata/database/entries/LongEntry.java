package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class LongEntry implements Entry {

    @Getter @Setter
    private long value;

    public LongEntry(long value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.LONG;
    }
}
