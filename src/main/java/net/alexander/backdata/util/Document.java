package net.alexander.backdata.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.alexander.backdata.database.EntryType;
import net.alexander.backdata.database.IEntry;
import net.alexander.backdata.database.INumberEntry;
import net.alexander.backdata.database.entries.ArrayEntry;
import net.alexander.backdata.database.entries.BooleanEntry;
import net.alexander.backdata.database.entries.CharacterEntry;
import net.alexander.backdata.database.entries.StringEntry;

public class Document {

    private JsonObject jsonObject;

    public Document() {
        this.jsonObject = new JsonObject();
    }

    public Document(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Document addString(String key, String value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document addNumber(String key, Number value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document addBoolean(String key, Boolean value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    public Document addArray(String key, ArrayEntry arrayEntry) {
        JsonArray jsonArray = new JsonArray();

        if(arrayEntry.getEntryType() == EntryType.STRING) {
            for(IEntry all : arrayEntry.getValue()) {
                jsonArray.add(((StringEntry) all).getValue());
            }

        } else if(arrayEntry.getEntryType() == EntryType.INTEGER
                || arrayEntry.getEntryType() == EntryType.DOUBLE
                || arrayEntry.getEntryType() == EntryType.LONG
                || arrayEntry.getEntryType() == EntryType.FLOAT
                || arrayEntry.getEntryType() == EntryType.BYTE
                || arrayEntry.getEntryType() == EntryType.SHORT) {

            for(IEntry all : arrayEntry.getValue()) {
                INumberEntry entry = (INumberEntry) all;
                jsonArray.add((Number) entry.getNumberValue());
            }
        } else if(arrayEntry.getEntryType() == EntryType.CHARACTER) {
            for(IEntry all : arrayEntry.getValue()) {
                jsonArray.add(((CharacterEntry) all).getValue());
            }
        } else if(arrayEntry.getEntryType() == EntryType.BOOLEAN) {
            for(IEntry all : arrayEntry.getValue()) {
                jsonArray.add(((BooleanEntry) all).isValue());
            }
        }

        return this;
    }

    public String getString(String key) {
        return this.jsonObject.get(key).getAsString();
    }

    public Number getNumber(String key) {
        return this.jsonObject.get(key).getAsNumber();
    }

    public JsonObject create() {
        return this.jsonObject;
    }

    public String toJSON() {
        return this.jsonObject.toString();
    }

}
