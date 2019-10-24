package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

import java.util.ArrayList;
import java.util.List;

public class ArrayEntry implements IEntry {

    @Getter
    @Setter
    private List<IEntry> value;
    @Getter
    private EntryType entryType;

    public ArrayEntry(EntryType entryType) {
        this.entryType = entryType;
        this.value = new ArrayList<>();
    }
    
    public void add(IEntry IEntry) {
        if(IEntry.getType() == entryType) {
            this.value.add(IEntry);
        }
    }

    @Override
    public EntryType getType() {
        return EntryType.ARRAY;
    }

}
