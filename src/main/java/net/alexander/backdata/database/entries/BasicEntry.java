package net.alexander.backdata.database.entries;

import net.alexander.backdata.database.DatabaseComponent;
import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class BasicEntry implements Entry, DatabaseComponent {


    @Override
    public EntryType getEntryType() {
        return EntryType.BASIC;
    }
}
