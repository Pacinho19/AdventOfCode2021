package pl.pacinho.adventofcode2021.challange.day9;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9Part2 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day9Part2().calculate("day9\\input.txt"));
    }

    private Integer[][] board;

    private HashMap<String, HashSet<String>> basins;

    @Override
    public long calculate(String filePath) {

        List<String> strings = FileUtils.readTxt(new File(filePath));
        parseBoard(strings);

        createBasins();
        return basins.values()
                .stream()
                .map(Set::size)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .subList(0, 3)
                .stream()
                .map(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    private void createBasins() {
        basins = new HashMap<>();
        IntStream.range(0, board.length)
                .forEach(row -> IntStream.range(0, board[0].length)
                        .forEach(col -> checkBasin(row, col)));
    }

    private void checkBasin(int row, int col) {
        Integer number = board[row][col];
        if (number == 9 || number == 8) return;

        HashSet<String> integers;
        String key = "";
        Optional<String> fullKey = basins.keySet().stream().filter(k -> k.contains(row + "," + col)).findFirst();
        if (fullKey.isPresent()) key = fullKey.get();
        integers = basins.get(key);

        List<String> toIncluding = checkValue(row, col);
        if (toIncluding.isEmpty()) return;

        Optional<String> newKey = toIncluding.stream().filter(k1 -> basins.keySet().stream().anyMatch(k -> k.contains(k1))).findFirst();
        if (newKey.isPresent()) {
            String key2 = newKey.get();
            Optional<String> fullKey2 = basins.keySet().stream().filter(k -> k.contains(key2)).findFirst();
            if (fullKey2.isPresent()) {
                String fullKey2S = fullKey2.get();
                HashSet<String> strings = basins.get(fullKey2S);
                strings.addAll(toIncluding);
                if (integers != null) strings.addAll(integers);
                strings.add(row + "," + col);
                basins.remove(key);
                basins.remove(fullKey2S);
                basins.put(strings.stream().collect(Collectors.joining(";")), strings);
                return;
            }
        }

        if (integers == null) integers = new HashSet<>();

        basins.remove(key);
        integers.add(row + "," + col);
        integers.addAll(toIncluding);
        basins.put(integers.stream().collect(Collectors.joining(";")), integers);
    }

    private List<String> checkValue(int row, int col) {
        Integer value = board[row][col];

        List<String> toCompare = new ArrayList<>();
        if (row - 1 >= 0 && board[row - 1][col] - 1 == value) toCompare.add(row - 1 + "," + col);
        if (row + 1 < board.length && board[row + 1][col] - 1 == value) toCompare.add(row + 1 + "," + col);
        if (col - 1 >= 0 && board[row][col - 1] - 1 == value) toCompare.add(row + "," + (col - 1));
        if (col + 1 < board[row].length && board[row][col + 1] - 1 == value) toCompare.add(row + "," + (col + 1));

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