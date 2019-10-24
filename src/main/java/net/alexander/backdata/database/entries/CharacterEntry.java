package net.alexander.backdata.database.entries;

import lombok.Getter;
import lombok.Setter;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;

public class CharacterEntry implements IEntry {

    @Getter
    @Setter
    private Character value;

    public CharacterEntry(Character value) {
        this.value = value;
    }

    @Override
    public EntryType getType() {
        return EntryType.CHARACTER;
    }
}
