package pl.pacinho.adventofcode2021.challange.day3;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3Part2 implements CalculateI {

    @Override
    public int calculate(String filePath) {
        List<String> lines = FileUtils.readTxt(new File(filePath));
        String oxygenRating = filterLines(lines, 0, true).get(0);
        String co2Rating = filterLines(lines, 0, false).get(0);
        return DecimalParser.parseFromBinary(oxygenRating) * DecimalParser.parseFromBinary(co2Rating);
    }

    private static List<String> filterLines(List<String> lines, int i, boolean more) {
        List<String> out = new ArrayList<>();
        lines.stream()
                .collect(Collectors.groupingBy(l -> l.charAt(i)))
                .forEach((c, list) -> {
                    if (more) {
                        if (list.size() > out.size() || list.size() == out.size() && c == '1') {
                            out.clear();
                            out.addAll(list);
                        }
                    } else {
                        if (out.isEmpty() || (!out.isEmpty() && list.size() < out.size()) || (!out.isEmpty() && list.size() == out.size() && c == '0')) {
                            out.clear();
                            out.addAll(list);
                        }
                    }
                });
        if (out.size() == 1) return out;
        else return filterLines(out, i + 1, more);
    }

    public static void main(String[] args) {
        System.out.println(new Day3Part2().calculate("day3\\input.txt"));
    }
}
