package pl.pacinho.adventofcode2021.challange.day9;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day9Part1 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day9Part1().calculate("day9\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<String> strings = FileUtils.readTxt(new File(filePath));
        Integer[][] board = parseBoard(strings);

        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sum += checkValue(i, j, board) + 1;
            }
        }
        return sum;
    }

    private int checkValue(int row, int col, Integer[][] board) {
        Integer value = board[row][col];
        if (value == 9) return -1;

        List<Integer> toCompare = new ArrayList<>();
        if (row - 1 >= 0) toCompare.add(board[row-1][col]);
        if (row + 1 < board.length) toCompare.add(board[row+1][col]);
        if (col - 1 >= 0) toCompare.add(board[row][col-1]);
        if (col + 1 < board[row].length) toCompare.add(board[row][col+1]);

        return toCompare.stream().allMatch(i -> i > value) ? value : -1;
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

}