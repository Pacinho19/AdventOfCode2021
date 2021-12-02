package pl.pacinho.adventofcode2021.challange.day2;

import lombok.Getter;

@Getter
public class Move {

    private Direction dir;
    private int range;

    public Move(String direction, int range) {
        this.range = range;
        this.dir = Direction.fromName(direction);
    }
}
