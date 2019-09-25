package net.alexander.backdata.database.entries;

import lombok.Getter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class NumberEntry implements Entry {

    @Getter private Number number;

    public NumberEntry(Number number) {
        this.number = number;
    }

    @Override
    public EntryType getType() {
        return EntryType.NUMBER;
    }
}
