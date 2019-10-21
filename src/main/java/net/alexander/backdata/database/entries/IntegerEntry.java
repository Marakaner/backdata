package net.alexander.backdata.database.entries;

import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class IntegerEntry implements Entry {


    @Override
    public EntryType getType() {
        return EntryType.INTEGER;
    }
}
