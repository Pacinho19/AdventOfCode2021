package pl.pacinho.adventofcode2021.challange.day14;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14Part1 implements CalculateI {

    private Map<String, String> pairMap;

    public static void main(String[] args) {
        System.out.println(new Day14Part1().calculate("day14\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<String> lines = FileUtils.readTxt(new File(filePath))
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        String state = lines.get(0);
        pairMap = lines.subList(1, lines.size())
                .stream()
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

        for (int i1 = 0; i1 < 10; i1++) {
            List<String> point = pointsByState(state);
            state = checkPoints(point);
        }


        char[] chars = state.toCharArray();
        List<Integer> collect1 = IntStream
                .range(0, chars.length)
                .mapToObj(i -> chars[i])
                .collect(Collectors.groupingBy(Character::charValue))
                .values()
                .stream()
                .map(List::size)
                .collect(Collectors.toList());
        return collect1.stream().max(Integer::compareTo).get() - collect1.stream().min(Integer::compareTo).get();
    }

    private String checkPoints(List<String> point) {
        return point.get(0).substring(0,1) + point.stream().map(s -> pairMap.get(s) + s.substring(1)).collect(Collectors.joining(""));
    }

    private List<String> pointsByState(String state) {
        List<String> out = new ArrayList<>();
        char[] chars = state.toCharArray();
        for (int i = 0; i < chars.length-1; i += 1) {
            out.add(state.substring(i, i + 2));
        }
        return out;
    }

}