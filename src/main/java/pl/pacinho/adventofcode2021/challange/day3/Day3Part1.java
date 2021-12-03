package pl.pacinho.adventofcode2021.challange.day3;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.challange.day2.Move;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Part1 implements CalculateI {

    @Override
    public int calculate(String filePath) {
        List<String> lines = FileUtils.readTxt(new File(filePath));

        StringBuilder gammaRate = new StringBuilder();
        for (int i = 0; i < lines.get(0).length(); i++) {
            int zero = 0;
            int one = 0;
            for (String line : lines) {
                char c = line.charAt(i);
                if (c == '0') zero++;
                else one++;
            }
            gammaRate.append(one > zero ? "1" : "0");
        }
        StringBuilder gammaRate2 = new StringBuilder();
        for (char c : gammaRate.toString().toCharArray()) gammaRate2.append(c == '0' ? "1" : "0");
        return DecimalParser.parseFromBinary(gammaRate.toString()) * DecimalParser.parseFromBinary(gammaRate2.toString());
    }



    public static void main(String[] args) {
        System.out.println(new Day3Part1().calculate("day3\\input.txt"));
    }
}
