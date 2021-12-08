package pl.pacinho.adventofcode2021.challange.day8;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day8Part1 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day8Part1().calculate("day8\\input.txt"));
    }

    private static List<Integer> uniqueLength = Arrays.asList(2, 3, 4, 7);

    @Override
    public long calculate(String filePath) {
        return FileUtils.readTxt(new File(filePath))
                .stream()
                .map(s -> s.split("\\|")[1].trim())
                .map(s -> s.split(" "))
                .flatMap(arr -> Arrays.stream(arr).filter(s -> uniqueLength.contains(s.length())))
                .count();
    }

}