package pl.pacinho.adventofcode2021.challange.day6;

import lombok.AllArgsConstructor;
import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day6Part1 implements CalculateI {

    private int daysLeft;

    public static void main(String[] args) {
        System.out.println(new Day6Part1(80).calculate("day6\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<Integer> states = Arrays.stream(FileUtils.readAsText(new File(filePath))
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while (daysLeft > 0) {
            List<Integer> newStates = new ArrayList<>();
            for (Integer state : states) {
                int newState = state - 1;
                if (newState < 0) {
                    newStates.add(6);
                    newStates.add(8);
                    continue;
                }
                newStates.add(newState);
            }

            states = newStates;
            daysLeft--;
        }

        return states.size();
    }

}