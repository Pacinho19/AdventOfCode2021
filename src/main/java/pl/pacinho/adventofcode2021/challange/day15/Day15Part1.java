package pl.pacinho.adventofcode2021.challange.day15;

import javafx.util.Pair;
import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Day15Part1 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day15Part1().calculate("day15\\input.txt"));
    }

    private int[][] board;
    private List<Pair<Integer, List<String>>> toCheck;
    private Integer minRisk = Integer.MAX_VALUE;

    @Override
    public long calculate(String filePath) {
        board = FileUtils.readTxt(new File(filePath))
                .stream()
                .map(s -> Arrays.stream(s.split(""))
                        .mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);


        toCheck = new ArrayList<>();
        toCheck.add(new Pair<>(0, Arrays.asList("0,0")));

        while (!toCheck.isEmpty()) {
            if (minRisk < Integer.MAX_VALUE)
                toCheck = toCheck.stream().filter(i -> i.getKey() < minRisk).collect(Collectors.toList());

            if (!toCheck.isEmpty()) checkPath(toCheck.get(toCheck.size() - 1));
        }

        return minRisk;
    }

    private void checkPath(Pair<Integer, List<String>> paths) {
        List<String> path = paths.getValue();
        Integer risk = paths.getKey();

        if (risk > minRisk) toCheck.remove(paths);

        if (path.get(path.size() - 1).equals(board.length - 1 + "," + (board[0].length - 1))) {
            if (risk < minRisk) {
                minRisk = risk;
            }
            toCheck.remove(paths);
            return;
        }

        List<String> possibleWays = getNextWays(path.get(path.size() - 1), path);
        toCheck.remove(paths);
        if (possibleWays.isEmpty()) return;

        for (String way : possibleWays) {
            addPath(path, way, risk);
        }
    }

    private void addPath(List<String> path, String way, Integer risk) {
        String[] next = way.split(",");
        List<String> newPath = new ArrayList<>(path);
        newPath.add(way);
        toCheck.add(new Pair<>(risk + board[Integer.parseInt(next[0])][Integer.parseInt(next[1])], newPath));
    }


    private List<String> getNextWays(String currentPos, List<String> path) {
        List<String> out = new ArrayList<>();

        String[] split = currentPos.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);

        if (row - 1 >= 0) out.add((row - 1) + "," + col);

        if (row + 1 < board.length) out.add((row + 1) + "," + col);

        if (col - 1 >= 0) out.add(row + "," + (col - 1));

        if (col + 1 < board[row].length) out.add(row + "," + (col + 1));

        return out.stream().filter(s -> !path.contains(s)).collect(Collectors.toList());
    }

}