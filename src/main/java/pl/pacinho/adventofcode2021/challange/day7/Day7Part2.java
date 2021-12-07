package pl.pacinho.adventofcode2021.challange.day7;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day7Part2 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day7Part2().calculate("day7\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<Integer> crabs = Arrays.stream(FileUtils.readAsText(new File(filePath))
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int maxPosition = crabs.stream().max(Integer::compareTo).get();
        List<Integer> fuelCosts = IntStream.rangeClosed(1, maxPosition)
                .mapToObj(i -> moveCrabs(i, crabs))
                .collect(Collectors.toList());
        return fuelCosts.stream().min(Integer::compareTo).get();
    }

    private Integer moveCrabs(int newPos, List<Integer> crabs) {
        return crabs.stream()
                .map(cPos -> calculateFuelCost(newPos, cPos))
                .reduce(0, Integer::sum);
    }

    private Integer calculateFuelCost(int newPos, int cPos) {
        return  IntStream.rangeClosed(1, Math.abs(newPos - cPos))
                .sum();
    }

}