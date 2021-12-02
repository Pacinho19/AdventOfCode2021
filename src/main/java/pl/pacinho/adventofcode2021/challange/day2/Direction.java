package pl.pacinho.adventofcode2021.challange.day2;

import java.util.Arrays;

public enum Direction {

    FORWARD("forward"),
    DOWN("down"),
    UP("up");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    public static Direction fromName(String nameS) {
        return Arrays.stream(values())
                .filter(d -> d.name.equals(nameS))
                .findFirst()
                .orElse(null);
    }
}
