package adventofcode2021.challange.day1;

import adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Day1Part2 {
    public static void main(String[] args) {
        List<String> numbers = FileUtils.readTxt(new File("day1\\inputPart2.txt"));
        if (numbers == null) {
            return;
        }

        int incrementCount = 0;
        int prevSum = 0;
        for (int i = 0; i < numbers.size() - 2; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                if (numbers.size() <= i + j) {
                    break;
                }
                sum += Integer.valueOf(numbers.get(i + j));
            }
            if (prevSum != 0) {
                incrementCount = incrementCount + (prevSum < sum ? 1 : 0);
            }
            prevSum = sum;
        }
        System.out.println(incrementCount);
    }

}
