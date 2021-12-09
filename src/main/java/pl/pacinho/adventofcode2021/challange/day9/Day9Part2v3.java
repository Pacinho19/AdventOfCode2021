package pl.pacinho.adventofcode2021.challange.day9;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9Part2v3 implements CalculateI {

    private static List<String> justVisited;
    private Integer[][] board;
    private List<List<Integer>> basins;

    public static void main(String[] args) {
        System.out.println(new Day9Part2v3().calculate("day9\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {

        List<String> strings = FileUtils.readTxt(new File(filePath));
        parseBoard(strings);

        createBasins();
        return basins
                .stream()
                .map(List::size)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .subList(0, 3)
                .stream()
                .map(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    private void createBasins() {
        justVisited = new ArrayList<>();
        basins = new ArrayList<>();
        IntStream.range(0, board.length)
                .forEach(row -> IntStream.range(0, board[0].length)
                        .forEach(col -> basins.add(checkBasin(row, col, new ArrayList<>()))));
    }

    private List<Integer> checkBasin(int row, int col, List<Integer> list) {
        if (justVisited.contains(row + "," + col)) return list;

        justVisited.add(row + "," + col);

        Integer number = board[row][col];
        if (number == 9) return list;

        list.add(number);
        List<String> toIncluding = checkValue(row, col);
        if (toIncluding.isEmpty()) return list;

        toIncluding.forEach(s ->checkBasin(Integer.parseInt(s.split(",")[0]),
                Integer.parseInt(s.split(",")[1]), list));

        return list;
    }


    private List<String> checkValue(int row, int col) {

        List<String> toCompare = new ArrayList<>();
        if (row - 1 >= 0 && board[row - 1][col] != 9) toCompare.add(row - 1 + "," + col);
        if (row + 1 < board.length && board[row + 1][col] != 9) toCompare.add(row + 1 + "," + col);
        if (col - 1 >= 0 && board[row][col - 1] != 9) toCompare.add(row + "," + (col - 1));
        if (col + 1 < board[row].length && board[row][col + 1] != 9) toCompare.add(row + "," + (col + 1));

        return toCompare;
    }

    private void parseBoard(List<String> strings) {
        board = new Integer[strings.size()][strings.get(0).length()];
        for (int i = 0; i < strings.size(); i++) {
            String[] split = strings.get(i).split("");
            for (int j = 0; j < split.length; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }
    }

}