package net.alexander.backdata.database;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.alexander.backdata.database.entries.*;
import net.alexander.backdata.network.Client;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.util.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseManager implements Service {

    @Getter
    private Map<String, Table> tables;

    public DatabaseManager() {
        this.tables = new HashMap<>();
    }

    public void handleRequest(Client client, JsonObject jsonObject) {
        Document document = new Document(jsonObject);

        UUID id = UUID.fromString(document.getString("id"));
        String[] args = document.getString("query").trim().split(" ");

        if (args[0].equalsIgnoreCase("get")) {
            String searchingKey = args[1];
            String[] given = args[5].split("=");
            String tableName = args[3];

            String givenKey = null;
            String givenValue = null;

            if (searchingKey == null || given.length == 0 || tableName == null) {
                sendError(client, id, "A wrong syntax in query.");
                return;
            } else {
                givenKey = given[0];
                givenValue = given[1];
            }

            if (this.tables.containsKey(tableName)) {
                Table table = this.tables.get(tableName);

                EntryType entryType = EntryType.getByName(document.getString("type"));

                IEntry returnValue = null;

                if (entryType == null) return;

                if (entryType == EntryType.STRING) {
                    returnValue = table.getDataSet(searchingKey, givenKey, givenValue);
                } else if (entryType == EntryType.INTEGER
                        || entryType == EntryType.DOUBLE
                        || entryType == EntryType.LONG
                        || entryType == EntryType.FLOAT
                        || entryType == EntryType.BYTE
                        || entryType == EntryType.SHORT) {
                    assert givenKey != null;
                    returnValue = table.getDataSet(searchingKey, givenKey, givenValue);
                } else if (entryType == EntryType.BOOLEAN) {
                    returnValue = table.getDataSet(searchingKey, givenKey, givenValue);
                } else if (entryType == EntryType.CHARACTER) {
                    returnValue = table.getDataSet(searchingKey, givenKey, givenValue);
                }

                if (returnValue == null) return;

                switch (returnValue.getType()) {
                    case ARRAY:
                        ArrayEntry arrayEntry = (ArrayEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addArray("value", arrayEntry).create());
                        break;

                    case INTEGER:
                        IntegerEntry integerEntry = (IntegerEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addNumber("value", integerEntry.getValue()).create());
                        break;

                    case LONG:
                        LongEntry longEntry = (LongEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addNumber("value", longEntry.getValue()).create());
                        break;

                    case BYTE:
                        ByteEntry byteEntry = (ByteEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addNumber("value", byteEntry.getValue()).create());
                        break;

                    case DOUBLE:
                        DoubleEntry doubleEntry = (DoubleEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addNumber("value", doubleEntry.getValue()).create());
                        break;

                    case FLOAT:
                        FloatEntry floatEntry = (FloatEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addNumber("value", floatEntry.getValue()).create());
                        break;

                    case STRING:
                        StringEntry stringEntry = (StringEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addString("value", stringEntry.getValue()).create());
                        break;

                    case BOOLEAN:
                        BooleanEntry booleanEntry = (BooleanEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addBoolean("value", booleanEntry.isValue()).create());
                        break;
                }
            } else {
                sendError(client, id, "Could not find database");
            }
        } else if (args[0].equalsIgnoreCase("set")) {

        }

    }

    private void sendError(Client client, UUID id, String message) {
        client.write(new Document()
                .addString("id", id.toString())
                .addString("type", "Error")
                .addString("value", message)
                .create());
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
