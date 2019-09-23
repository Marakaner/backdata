package net.alexander.backdata.database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class DatabaseManager {

    private Database database;

    public DatabaseManager() {
        this.database = new Database();
    }

    public void getDatabaseComponent(String query, Consumer<DatabaseComponent> consumer) {
        new Thread(() -> {
            String[] args = query.split(" ");

            if(args[0].equalsIgnoreCase("get") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("exist")) {
                Table table = this.database.getTable(args[1].toLowerCase());
                if(table != null) {

                } else {

                }
            } else {
                consumer.accept(new FailedRequest("Can't resolve query", RequestError.QUERY_ERROR));
            }

        }).start();
    }

}
