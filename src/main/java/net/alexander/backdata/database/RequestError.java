package net.alexander.backdata.database;

import java.util.List;

public enum RequestError {

    CANNOT_FIND_TABLE(1),
    CANNOT_FIND_DATASET(2),
    CANNOT_FIND_KEY(3),
    QUERY_ERROR(4);

    private int number;

    RequestError(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
