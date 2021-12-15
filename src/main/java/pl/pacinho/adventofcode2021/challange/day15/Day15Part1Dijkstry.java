package pl.pacinho.adventofcode2021.challange.day15;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15Part1Dijkstry implements CalculateI {

    private int[][] board;
    private int rowCount;
    private int colCount;

    private LinkedHashMap<String, Integer> dV;
    private LinkedHashMap<String, String> pV;
    private List<String> completed;

    public static void main(String[] args) {
        System.out.println(new Day15Part1Dijkstry().calculate("day15\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        board = FileUtils.readTxt(new File(filePath))
                .stream()
                .map(s -> Arrays.stream(s.split(""))
                        .mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);

        rowCount = board.length;
        colCount = board[0].length;

        completed = new ArrayList<>();

        fill_dV();
        fill_pV();

        while (completed.size() < rowCount * colCount) {
            if(completed.size()%100==0){
                System.out.println(completed.size());
            }
            String key = Collections.min(dV.entrySet().stream().filter(k -> !completed.contains(k.getKey())).collect(Collectors.toList()), Map.Entry.comparingByValue()).getKey();
            checkPoint(key);
        }

        return dV.get(rowCount - 1 + "," + (colCount - 1));
    }

    private void checkPoint(String point) {
        List<String> nextWays = getNextWays(point);
        nextWays.forEach(newPoint -> {
            Integer cost = getCost(newPoint);
            Integer oldPointCost = dV.get(newPoint);

            if (dV.get(point) + cost < oldPointCost) {
                dV.put(newPoint, dV.get(point) + cost);
                pV.put(newPoint, point);
            }
        });
        completed.add(point);
    }

    private Integer getCost(String point) {
        String[] split = point.split(",");
        return board[Integer.parseInt(split[0])][Integer.parseInt(split[1])];
    }

    private void fill_pV() {
        pV = new LinkedHashMap<>();
        IntStream.range(0, rowCount)
                .forEach(row -> IntStream.range(0, colCount)
                        .forEach(col -> pV.put(row + "," + col, "-")));
    }

    private void fill_dV() {
        dV = new LinkedHashMap<>();
        IntStream.range(0, rowCount)
                .forEach(row -> IntStream.range(0, colCount)
                        .forEach(col -> dV.put(row + "," + col, Integer.MAX_VALUE)));
        dV.put("0,0", 0);
    }


    private List<String> getNextWays(String currentPos) {
        List<String> out = new ArrayList<>();

        String[] split = currentPos.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);

        if (row - 1 >= 0) out.add((row - 1) + "," + col);

        if (row + 1 < board.length) out.add((row + 1) + "," + col);

        if (col - 1 >= 0) out.add(row + "," + (col - 1));

        if (col + 1 < board[row].length) out.add(row + "," + (col + 1));

        return out;
    }

}