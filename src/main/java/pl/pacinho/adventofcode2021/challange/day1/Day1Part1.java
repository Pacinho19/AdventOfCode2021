package pl.pacinho.adventofcode2021.challange.day1;

import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.List;

public class Day1Part1 {

    public static void main(String[] args) {
        List<String> numbers = FileUtils.readTxt(new File("day1\\input.txt"));
        int incrementCount = 0;
        for (int i = 1; i < numbers.size(); i++) {
            int currentNumber = Integer.parseInt(numbers.get(i));
            int previousNumber = Integer.parseInt(numbers.get(i - 1));
            if (currentNumber > previousNumber)
                incrementCount++;
        }
        System.out.println(incrementCount);
    }
}
