package pl.pacinho.adventofcode2021.challange.day4;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Bingo {

    private List<Cell> cells;
    @Setter
    private boolean win;

    public Bingo(List<Cell> cells) {
        this.cells = cells;
        win = false;
    }
}