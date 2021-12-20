package pl.pacinho.adventofcode2021.challange.day20;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day20Part1 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day20Part1().calculate("day20\\input.txt"));
    }


    private String algorithm;

    @Override
    public long calculate(String filePath) {

        List<String> lines = FileUtils.readTxt(new File(filePath));

        algorithm = lines.get(0);

        Character[][] chars = extendArray(lines
                .subList(1, lines.size())
                .stream()
                .filter(s -> !s.isEmpty())
                .map(String::toCharArray)
                .toArray(char[][]::new));

        printArray(chars);

        IntStream.range(0, 2)
                .forEach(i -> enhancement(chars));

        return Arrays.stream(chars)
                .flatMap(Arrays::stream)
                .filter(ch -> ch == '#').count();

    }

    private void enhancement(Character[][] chars) {

        for (int row = 0; row < chars.length; row++) {
            for (int col = 0; col < chars[row].length; col++) {
                List<Character> neighbors = getNeighbors(row, col, chars);
                if (neighbors.size() != 9) continue;
                chars[row][col] = checkNewValue(neighbors);
            }
        }

        printArray(chars);
    }

    private void printArray(Character[][] chars) {
        StringBuilder sb = new StringBuilder("");
        for (Character[] aChar : chars) {
            for (Character character : aChar) {
                sb.append(character);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private Character checkNewValue(List<Character> neighbors) {
        String binary = neighbors.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
        return algorithm.charAt(getDecimal(binary));
    }

    private List<Character> getNeighbors(int row, int col, Character[][] chars) {
        List<Character> out = new ArrayList<>();

        if (row - 1 > 0 && col - 1 > 0) out.add(chars[row - 1][col - 1]);
        if (row - 1 > 0) out.add(chars[row - 1][col]);
        if (row - 1 > 0 && col + 1 < chars[row].length) out.add(chars[row - 1][col + 1]);

        if (col - 1 > 0) out.add(chars[row][col - 1]);
        out.add(chars[row][col]);
        if (col + 1 < chars[row].length) out.add(chars[row][col + 1]);

        if (row + 1 < chars.length && col - 1 > 0) out.add(chars[row + 1][col - 1]);
        if (row + 1 < chars.length) out.add(chars[row + 1][col]);
        if (row + 1 < chars.length && col + 1 < chars[row].length) out.add(chars[row + 1][col + 1]);

        return out.stream().anyMatch(ch -> ch == '#') ? out : new ArrayList<>();
    }

    private Character[][] extendArray(char[][] toArray) {
        Character[][] newArray = new Character[toArray.length * 3][toArray[0].length * 3];
        Arrays.stream(newArray).forEach(a -> Arrays.fill(a, '.'));

        for (int i = 0; i < toArray.length; i++)
            for (int j = 0; j < toArray[i].length; j++)
                newArray[i + newArray.length / 3][j + newArray[i].length / 3] = toArray[i][j];

        return newArray;
    }

    private int getDecimal(String binary) {
        int sum = 0;
        int pos = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            sum += getDigitAtPos(binary, i) * Math.pow(2, pos);
            pos++;
        }
        return sum;
    }

    private int getDigitAtPos(String bits, int pos) {
        return String.valueOf(bits.charAt(pos)).equals(".") ? 0 : 1;
    }

}