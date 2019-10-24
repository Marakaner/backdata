package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.INumberEntry;

public class ByteEntry implements INumberEntry {

    @Getter
    @Setter
    private Byte value;

    public ByteEntry(Byte value) {
        this.value = value;
    }


    @Override
    public EntryType getType() {
        return EntryType.BYTE;
    }

    @Override
    public Object getNumberValue() {
        return this.value;
    }
}
