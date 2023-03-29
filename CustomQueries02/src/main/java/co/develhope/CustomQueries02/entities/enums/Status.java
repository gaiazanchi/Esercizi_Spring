package co.develhope.CustomQueries02.entities.enums;

import java.util.Random;

public enum Status {
    ONTIME,
    DELAYED,
    CANCELLED;

    private static final Random random = new Random();

    public static Status randomStatus(){
        Status[] statuses = values();
        return statuses[random.nextInt(statuses.length)];
    }
}
