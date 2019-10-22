package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class FloatEntry implements Entry {

    @Getter @Setter
    private Float value;

    public FloatEntry(Float value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.FLOAT;
    }
}
