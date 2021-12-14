package pl.pacinho.adventofcode2021.challange.day14;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14Part2 implements CalculateI {

    private Map<String, String> rules;
    private Map<String, Long> pairs;

    private int iteration;

    public Day14Part2(int iteration) {
        this.iteration = iteration;
    }

    public static void main(String[] args) {
        System.out.println(new Day14Part2(40).calculate("day14\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<String> lines = FileUtils.readTxt(new File(filePath))
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        String template = lines.get(0);
        rules = lines.subList(1, lines.size())
                .stream()
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

        initPairs(template);

        IntStream.range(0, iteration)
                .forEach(i -> {
                    Map<String, Long> newPairs = new HashMap<>(pairs);
                    pairs.forEach((pair, count) -> {
                        String newPair = pair.substring(0, 1) + rules.get(pair);
                        if (newPairs.containsKey(newPair)) newPairs.put(newPair, count + newPairs.get(newPair));
                        else newPairs.put(newPair, count);

                        String newPair2 = rules.get(pair) + pair.substring(1);
                        if (newPairs.containsKey(newPair2)) newPairs.put(newPair2, count + newPairs.get(newPair2));
                        else newPairs.put(newPair2, count);

                        newPairs.put(pair, newPairs.get(pair) - count);
                    });
                    pairs = newPairs;
                });

        HashMap<String, Long> finalMap = new HashMap<>();
        pairs.forEach((k, v) -> {
            if (v < 0) return;
            String split = k.split("")[0];
            Long aLong = finalMap.get(split);
            if (aLong == null) aLong = 0L;
            finalMap.put(split, aLong + v);
        });

        String lastLetter = String.valueOf(template.charAt(template.length() - 1));
        Long aLong = finalMap.get(lastLetter);
        finalMap.put(lastLetter, aLong + 1);

        return finalMap.values().stream().max(Long::compareTo).get() - finalMap.values().stream().min(Long::compareTo).get();
    }

    private void initPairs(String template) {
        pairs = new HashMap<>();
        char[] chars = template.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 1) {
            String pair = template.substring(i, i + 2);
            Long integer = pairs.get(pair);
            if (integer == null) integer = 0L;
            pairs.put(pair, integer + 1);
        }
    }

}