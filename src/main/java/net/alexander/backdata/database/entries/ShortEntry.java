package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class ShortEntry implements IEntry {

    @Getter
    @Setter
    private short value;

    public ShortEntry(short value) {
        this.value = value;
    }


    @Override
    public EntryType getType() {
        return EntryType.SHORT;
    }

    @Override
    public Object getGlobalValue() {
        return this.value;
    }
}
