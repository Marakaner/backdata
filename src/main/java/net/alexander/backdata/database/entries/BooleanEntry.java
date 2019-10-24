package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class BooleanEntry implements IEntry {

    @Getter
    @Setter
    private boolean value;

    public BooleanEntry(boolean value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.BOOLEAN;
    }
}
