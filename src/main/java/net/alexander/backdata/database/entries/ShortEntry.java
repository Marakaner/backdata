package net.alexander.backdata.database.entries;

import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class ShortEntry implements Entry {

    private short value;

    public ShortEntry(short value) {
        this.value = value;
    }


    @Override
    public EntryType getType() {
        return EntryType.SHORT;
    }
}
