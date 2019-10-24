package net.alexander.backdata.database.entries;

import lombok.Getter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class StringEntry implements IEntry {

    @Getter
    private String value;

    public StringEntry(String value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.STRING;
    }
}
