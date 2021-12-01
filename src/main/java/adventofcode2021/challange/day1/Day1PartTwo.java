package adventofcode2021.challange.day1;

import adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Day1PartTwo {
    public static void main(String[] args) {
        List<String> numbers = FileUtils.readTxt(new File("day1\\inputPart2.txt"));

        if (numbers == null) {
            return;
        }

        List<Integer> numbers2 = new ArrayList<>();
        for (int i = 0; i < numbers.size() - 2; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                if (numbers.size() <= i + j) {
                    break;
                }
                sum += Integer.valueOf(numbers.get(i + j));
            }
            numbers2.add(sum);
        }
//      numbers2.forEach(System.out::println);

        int incrementCount = 0;
        for (int i = 0; i < numbers2.size(); i++) {
            if (i + 1 == numbers2.size()) {
                break;
            }
            incrementCount = incrementCount+ (numbers2.get(i)<numbers2.get(i+1) ? 1 : 0);
        }
        System.out.println(incrementCount);
    }

}
