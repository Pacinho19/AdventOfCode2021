package pl.pacinho.adventofcode2021.challange.day10;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10Part1 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day10Part1().calculate("day10\\input.txt"));
    }

    private HashMap<Character, Long> pointsMap = new HashMap<Character, Long>() {
        {
            put(')', 3L);
            put(']', 57L);
            put('}', 1197L);
            put('>', 25137L);
        }
    };

    @Override
    public long calculate(String filePath) {
        List<char[]> lines = FileUtils.readTxt(new File(filePath))
                .stream()
                .map(s -> s.toCharArray())
                .collect(Collectors.toList());

        List<Bracket> illegalCharacters = new ArrayList<>();

        lines.forEach(arr -> {
            List<Bracket> openBracket = new ArrayList<>();
            for (char c : arr) {
                Bracket bracket = Bracket.findByChar(c);
                if (bracket.isOpen()) {
                    openBracket.add(bracket);
                    continue;
                }

                if (openBracket.get(openBracket.size() - 1).getBracketType() == bracket.getBracketType()) {
                    openBracket.remove(openBracket.size() - 1);
                    continue;
                }
                illegalCharacters.add(bracket);
                break;
            }
        });

        return illegalCharacters.stream()
                .collect(Collectors.groupingBy(Bracket::getSign))
                .values()
                .stream()
                .map(l -> l.size() * pointsMap.get(l.get(0).getSign()))
                .reduce(0L, Long::sum);
    }

}