package net.alexander.backdata.database.entries;

import net.alexander.backdata.database.Entry;
import net.alexander.backdata.database.EntryType;

public class CharacterEntry implements Entry {

    private Character value;

    public CharacterEntry(Character value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.CHARACTER;
    }
}
