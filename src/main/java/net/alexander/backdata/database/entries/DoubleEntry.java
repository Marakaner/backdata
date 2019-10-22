package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class DoubleEntry implements Entry {

    @Getter @Setter
    private Double value;

    public DoubleEntry(Double value) {
        this.value = value;
    }


    @Override
    public EntryType getType() {
        return EntryType.DOUBLE;
    }
}
