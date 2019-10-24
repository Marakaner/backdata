package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.INumberEntry;

public class ShortEntry implements INumberEntry {

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
    public Object getNumberValue() {
        return this.value;
    }
}
