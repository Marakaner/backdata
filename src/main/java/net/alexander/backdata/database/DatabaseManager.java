package net.alexander.backdata.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Tab;
import lombok.Builder;
import lombok.Getter;
import net.alexander.backdata.BackData;
import net.alexander.backdata.database.entries.*;
import net.alexander.backdata.network.Client;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.util.Document;
import net.alexander.backdata.util.ErrorMessages;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseManager implements Service {


    private File directory;
    private Gson gson;

    @Getter
    private Map<String, Table> tables;

    public DatabaseManager(String dir) {

        this.tables = new HashMap<>();
        gson = BackData.getInstance().getPublicGson();

        directory = new File(dir);
        if(directory.listFiles().length == 0) {

            for(File file : directory.listFiles()) {
                Table table = null;
                try {
                    table = gson.fromJson(new FileReader(file), new TypeToken<Table>() {}.getType());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                this.tables.put(table.getName(), table);
            }
        }
    }

    public void saveDatabase() {
        for(String table : tables.keySet()) {
            File file = new File(directory.getPath() + "/" + table);
                try {
                    if(file.exists()) file.createNewFile();

                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(gson.toJson(this.tables.get(table)));
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public Table getTable(String name) {
        return this.tables.get(name);
    }

    public void handleRequest(Client client, JsonObject jsonObject) {
        Document document = new Document(jsonObject);

        UUID id = UUID.fromString(document.getString("id"));
        String[] args = document.getString("query").trim().split(" ");

        if (args[0].equalsIgnoreCase("get")) {
            get(client, document);
        } else if (args[0].equalsIgnoreCase("set")) {
            set(client, document);
        }

    }

    private void set(Client client, Document document) {

        String[] args = document.getString("query").trim().split(" ");
        UUID id = UUID.fromString(document.getString("id"));

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

        if(tables.containsKey(tableName)) {
            Table table = tables.get(tableName);

            EntryType type = EntryType.getByName(document.getString("type"));

            switch(type) {

            }

        } else {
            sendError(client, id, ErrorMessages.TABLE_COULD_NOT_BE_FOUND);
        }
    }

    /**
     * The internal method for a GET-Query.
     * @param client
     * @param document
     */
    private void get(Client client, Document document) {

        String[] args = document.getString("query").trim().split(" ");
        UUID id = UUID.fromString(document.getString("id"));

        String searchingKey = args[1];
        String[] given = args[5].split("=");
        String tableName = args[3];

        String givenKey;
        String givenValue;

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

            IEntry returnValue = table.getDataSet(searchingKey, givenKey, givenValue);

            if (entryType == null) return;

            if (returnValue == null) return;

            switch (returnValue.getType()) {
                case ARRAY:
                    ArrayEntry arrayEntry = (ArrayEntry) returnValue;
                    client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addString("arrayType", arrayEntry.getEntryType().getName()).addArray("value", arrayEntry).create());
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

                case SHORT:
                    ShortEntry shortEntry = (ShortEntry) returnValue;
                    client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addNumber("value", shortEntry.getValue()).create());
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
            sendError(client, id, ErrorMessages.TABLE_COULD_NOT_BE_FOUND);
        }
    }

    private void sendError(Client client, UUID id, String message) {
        client.write(new Document()
                .addString("id", id.toString())
                .addString("type", "Error")
                .addString("value", message)
                .create());
    }

    public boolean isTableExist(String name) {
        if(this.tables.containsKey(name)) {
            return true;
        }
        return false;
    }

    public void createTable(String name) {
        if(!isTableExist(name)) {
            this.tables.put(name, new Table(name));
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
