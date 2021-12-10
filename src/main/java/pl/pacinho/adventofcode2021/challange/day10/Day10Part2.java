package pl.pacinho.adventofcode2021.challange.day10;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Day10Part2 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day10Part2().calculate("day10\\input.txt"));
    }

    private HashMap<Character, Long> pointsMap = new HashMap<Character, Long>() {
        {
            put('(', 1L);
            put('[', 2L);
            put('{', 3L);
            put('<', 4L);
        }
    };

    @Override
    public long calculate(String filePath) {
        List<char[]> lines = FileUtils.readTxt(new File(filePath))
                .stream()
                .map(String::toCharArray)
                .collect(Collectors.toList());

        List<List<Bracket>> notClosingLines = lines.stream()
                .map(this::getNotClosingBrackets)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<Long> scores = notClosingLines.stream()
                .map(this::calculateScore)
                .sorted()
                .collect(Collectors.toList());

        return scores.get((scores.size() / 2));
    }

    private Long calculateScore(List<Bracket> brackets) {
        long score = 0L;
        Collections.reverse(brackets);
        for (Bracket b : brackets) score = score * 5 + pointsMap.get(b.getSign());
        return score;
    }

    private LinkedList<Bracket> getNotClosingBrackets(char[] arr) {
        LinkedList<Bracket> openBracket = new LinkedList<>();
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
            return null;
        }
        return openBracket;
    }

}