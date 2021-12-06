package pl.pacinho.adventofcode2021.challange.day2;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Part1 implements CalculateI {

    @Override
    public long calculate(String filePath) {
        List<Move> moves = FileUtils.readTxt(new File(filePath))
                .stream()
                .map(l -> l.split(" "))
                .map(arr -> new Move(arr[0].trim(), Integer.parseInt(arr[1].trim())))
                .collect(Collectors.toList());

        int horizontalPos = 0;
        int depth = 0;
        for (Move m : moves) {
            switch (m.getDir()) {
                case FORWARD:
                    horizontalPos += m.getRange();
                    break;
                case DOWN:
                    depth += m.getRange();
                    break;
                case UP:
                    depth -= m.getRange();
                    break;
            }
        }
        return horizontalPos * depth;
    }

    public static void main(String[] args) {
        System.out.println(new Day2Part1().calculate("day2\\input.txt"));
    }
}