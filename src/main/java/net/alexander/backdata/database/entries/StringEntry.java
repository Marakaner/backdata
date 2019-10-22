package net.alexander.backdata.database.entries;

import lombok.Getter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class StringEntry implements Entry {

    @Getter private String value;

    public StringEntry(String value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.STRING;
    }
}
