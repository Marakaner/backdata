package net.alexander.backdata.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

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

    public Document addArray(String key, String... value) {
        addStringList(key, Arrays.asList(value));
        return this;
    }

    public Document addStringList(String key, List<String> value) {
        JsonArray jsonArray = new JsonArray();
        for(String all : value) {
            jsonArray.add(all);
        }
        this.jsonObject.add(key, jsonArray);
        return this;
    }

    public Document addArray(String key, Number... value) {
        addNumberList(key, Arrays.asList(value));
        return this;
    }

    public Document addNumberList(String key, List<Number> value) {
        JsonArray jsonArray = new JsonArray();
        for(Number all : value) {
            jsonArray.add(all);
        }
        this.jsonObject.add(key, jsonArray);
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
