package pl.pacinho.adventofcode2021.challange.day4;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4Part1 implements CalculateI {

    private static final int colCount = 5;
    private static final int rowCount = 5;

    public static void main(String[] args) {
        System.out.println(new Day4Part1().calculate("day4\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        String lines = FileUtils.readAsText(new File(filePath));
        List<String> split = Arrays.stream(lines.split("\n\n")).collect(Collectors.toList());
        List<Integer> numbers = Arrays.stream(split.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        split.remove(0);

        List<Bingo> boards = split.stream()
                .map(BingoUtils::parseBoard)
                .collect(Collectors.toList());
        Bingo winningBoard = null;
        int round = 0;
        int number = 0;
        while (winningBoard == null) {
            number = numbers.get(round);
            winningBoard = checkBoards(boards, number);
            round++;
        }

        return winningBoard
                .getCells()
                .stream()
                .filter(c -> !c.isSelected())
                .map( Cell::getValue)
                .reduce(0, Integer::sum)
                * number;
    }

    private Bingo checkBoards(List<Bingo> boards, Integer number) {
        for (Bingo bingo : boards) {
            List<Cell> board = bingo.getCells();
            board.stream().filter(c -> number == c.getValue())
                    .forEach(Cell::setSelected);
            boolean win = BingoUtils.checkWin(board);
            if (win) return bingo;
        }
        return null;
    }


}