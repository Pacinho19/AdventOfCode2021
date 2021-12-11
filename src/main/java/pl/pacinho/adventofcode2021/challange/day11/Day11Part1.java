package pl.pacinho.adventofcode2021.challange.day11;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day11Part1 implements CalculateI {

    private Integer[][] board;
    private List<String> justFlashes;
    private long flashesCount = 0;

    public static void main(String[] args) {
        System.out.println(new Day11Part1().calculate("day11\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<String> strings = FileUtils.readTxt(new File(filePath));
        board = parseBoard(strings);

        //printBoard();
        IntStream.range(0, 100)
                .forEach(i -> checkSteps());

        return flashesCount;
    }

    private void checkSteps() {
        justFlashes = new ArrayList<>();
        IntStream.range(0, board.length)
                .forEach(row -> IntStream.range(0, board[row].length)
                        .forEach(col -> {
                            checkEnergy(row, col);
                        }));
        //printBoard();
    }

    private void checkEnergy(int row, int col) {
        if (justFlashes.contains(row + "," + col)) return;

        Integer value = board[row][col];
        if (value != 9) {
            board[row][col] = value + 1;
            return;
        }
        flashes(row, col);
    }

    private void flashes(int row, int col) {
        justFlashes.add((row + "," + col));
        board[row][col] = 0;
        checkValue(row, col);
        flashesCount++;
    }

    private void checkValue(int row, int col) {
        if (row - 1 >= 0) checkEnergy(row - 1, col);

        if (row + 1 < board.length) checkEnergy(row + 1, col);

        if (col - 1 >= 0) checkEnergy(row, col - 1);

        if (col + 1 < board[row].length) checkEnergy(row, col + 1);

        if (row + 1 < board.length && col + 1 < board[row].length) checkEnergy(row + 1, col + 1);

        if (row - 1 >= 0 && col + 1 < board[row].length) checkEnergy(row - 1, col + 1);

        if (row + 1 < board.length && col - 1 >= 0) checkEnergy(row + 1, col - 1);

        if (row - 1 >= 0 && col - 1 >= 0) checkEnergy(row - 1, col - 1);
    }


    private Integer[][] parseBoard(List<String> strings) {
        Integer[][] out = new Integer[strings.size()][strings.get(0).length()];
        for (int i = 0; i < strings.size(); i++) {
            String[] split = strings.get(i).split("");
            for (int j = 0; j < split.length; j++) {
                out[i][j] = Integer.parseInt(split[j]);
            }
        }
        return out;
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}