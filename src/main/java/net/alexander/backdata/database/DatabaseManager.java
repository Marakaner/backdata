package net.alexander.backdata.database;

import com.google.gson.JsonObject;
import net.alexander.backdata.network.Client;
import net.alexander.backdata.service.Service;
import net.alexander.backdata.util.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseManager implements Service {

    private Map<String, Table> tables;

    public DatabaseManager() {
        this.tables = new HashMap<>();
    }

    public void handleRequest(Client client, JsonObject jsonObject) {
        Document document = new Document(jsonObject);

        UUID id = UUID.fromString(document.getString("id"));
        String[] args = document.getString("query").trim().split(" ");

        if(args[0].equalsIgnoreCase("get")) {
            String tableName = args[3];
            if(this.tables.containsKey(tableName)) {
                Table table = this.tables.get(tableName);
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
