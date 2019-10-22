package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class ByteEntry implements Entry {

    @Getter @Setter
    private Byte value;

    public ByteEntry(Byte value) {
        this.value = value;
    }


    @Override
    public EntryType getType() {
        return EntryType.BYTE;
    }
}
