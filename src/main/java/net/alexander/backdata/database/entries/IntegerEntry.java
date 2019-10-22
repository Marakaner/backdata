package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class IntegerEntry implements Entry {

    @Getter @Setter
    private Integer value;

    public IntegerEntry(int value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.INTEGER;
    }
}
