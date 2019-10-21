package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

import java.util.ArrayList;
import java.util.List;

public class ArrayEntry implements Entry {

    @Getter @Setter private List<Entry> entries;
    @Getter private EntryType entryType;

    public ArrayEntry(EntryType entryType) {
        this.entryType = entryType;
        this.entries = new ArrayList<>();
    }

    @Override
    public EntryType getType() {
        return EntryType.ARRAY;
    }

}
