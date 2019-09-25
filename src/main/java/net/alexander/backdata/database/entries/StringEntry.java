package net.alexander.backdata.database.entries;

import lombok.Getter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class StringEntry implements Entry {

    @Getter private String string;

    public StringEntry(String string) {
        this.string = string;
    }

    @Override
    public EntryType getType() {
        return EntryType.NUMBER;
    }
}
