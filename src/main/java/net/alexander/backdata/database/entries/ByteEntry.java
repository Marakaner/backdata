package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class ByteEntry implements IEntry {

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
    public Object getGlobalValue() {
        return this.value;
    }


}
