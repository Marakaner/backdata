package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class BooleanEntry implements Entry {

    @Getter @Setter
    private boolean value;

    public BooleanEntry(boolean value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.BOOLEAN;
    }
}
