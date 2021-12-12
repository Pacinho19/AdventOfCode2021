package pl.pacinho.adventofcode2021.challange.day12;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Path {

    private String start;
    private String end;

    @Override
    public String toString() {
        return "-" + end;
    }
}