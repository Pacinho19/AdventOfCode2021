package pl.pacinho.adventofcode2021.challange.day1;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.challange.day2.Day2Part1;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.List;

public class Day1Part2 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day1Part2().calculate("day2\\input.txt"));
    }

    @Override
    public int calculate(String filePath) {
        List<String> numbers = FileUtils.readTxt(new File(filePath));
        int incrementCount = 0;
        int prevSum = 0;
        for (int i = 0; i < numbers.size() - 2; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += Integer.valueOf(numbers.get(i + j));
            }
            if (prevSum != 0) {
                incrementCount = incrementCount + (prevSum < sum ? 1 : 0);
            }
            prevSum = sum;
        }
        return incrementCount;
    }
}
