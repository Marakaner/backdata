package net.alexander.backdata.database;

public enum EntryType {

    ARRAY("Array"),
    BOOLEAN("Boolean"),
    STRING("String"),
    NUMBER("Number"),
    SHORT("Short"),
    BYTE("Byte"),
    CHARACTER("Character"),
    INTEGER("Integer"),
    LONG("Long"),
    DOUBLE("Double"),
    ERROR("Error");

    private String name;

    EntryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EntryType getByName(String name) {
        for(EntryType all : EntryType.values()) {
            if(all.getName().equalsIgnoreCase(name)) {
                return all;
            }
        }
        return null;
    }

}
