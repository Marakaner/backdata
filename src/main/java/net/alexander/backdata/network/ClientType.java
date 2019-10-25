package net.alexander.backdata.network;

public enum ClientType {

    WEBSITE("Website"),
    CLIENT("Client");

    private String name;

    ClientType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ClientType getByName(String name) {
        for(ClientType all : ClientType.values()) {
            if(all.getName().equalsIgnoreCase(name)) {
                return all;
            }
        }
        return null;
    }
}
