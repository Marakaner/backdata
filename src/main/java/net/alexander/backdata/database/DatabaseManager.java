package net.alexander.backdata.database;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.alexander.backdata.database.entries.ArrayEntry;
import net.alexander.backdata.database.entries.LongEntry;
import net.alexander.backdata.event.EventManager;
import net.alexander.backdata.network.Client;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.service.ServiceManager;
import net.alexander.backdata.util.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseManager implements Service {

    EventManager eventManager = ServiceManager.getService(EventManager.class);

    @Getter private Map<String, Table> tables;

    public DatabaseManager() {
        this.tables = new HashMap<>();
    }

    public void handleRequest(Client client, JsonObject jsonObject) {
        Document document = new Document(jsonObject);

        UUID id = UUID.fromString(document.getString("id"));
        String[] args = document.getString("query").trim().split(" ");

        if(args[0].equalsIgnoreCase("get")) {
            String searchingKey = args[1];
            String[] given = args[5].split("=");
            String tableName = args[3];

            String givenKey = null;
            String givenValue = null;

            if(searchingKey == null || given.length == 0 || tableName == null) {
                sendError(client, id, "A wrong syntax in query.");
                return;
            } else {
                givenKey = given[0];
                givenValue = given[1];
            }

            if(this.tables.containsKey(tableName)) {
                Table table = this.tables.get(tableName);

                EntryType entryType = EntryType.getByName(document.getString("type"));

                Entry returnValue = null;

                if(entryType == EntryType.STRING) {
                    returnValue = table.getDataSet(searchingKey, givenKey, givenValue);
                } else if(entryType == EntryType.NUMBER) {
                    assert givenKey != null;
                    returnValue = table.getDataSet(searchingKey, givenKey, Double.parseDouble(givenKey));
                }

                assert returnValue != null;
                switch (returnValue.getType()) {
                    case ARRAY:
                        ArrayEntry arrayEntry = (ArrayEntry) returnValue;
                        if(arrayEntry.getEntryType() == EntryType.NUMBER) {

                        } else {

                        }
                        break;

                    case NUMBER:
                        LongEntry longEntry = (LongEntry) returnValue;
                        client.write(new Document().addString("id", id.toString()).addString("type", returnValue.getType().getName()).addArray("value", longEntry.getValue()).create());
                        break;

                    case STRING:

                        break;

                    case BOOLEAN:

                        break;
                }
            } else {
                sendError(client, id, "Could not find database");
            }
        } else if(args[0].equalsIgnoreCase("set")) {

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
