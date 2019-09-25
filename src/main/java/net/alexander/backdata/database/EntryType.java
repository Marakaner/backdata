package net.alexander.backdata.database;

public enum EntryType {

    ARRAY("Array"),
    TEXT("Text"),
    NUMBER("Number");

    private String name;

    EntryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public EntryType getByName(String name) {
        for(EntryType all : EntryType.values()) {
            if(all.getName().equalsIgnoreCase(name)) {
                return all;
            }
        }
        return null;
    }

}
