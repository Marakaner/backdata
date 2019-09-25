package net.alexander.backdata.database.entries;

import lombok.Getter;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayEntry implements Entry {

    @Getter private List<Entry> entries;

    public ArrayEntry() {
        this.entries = new ArrayList<>();
    }

    @Override
    public EntryType getType() {
        return EntryType.ARRAY;
    }

}
