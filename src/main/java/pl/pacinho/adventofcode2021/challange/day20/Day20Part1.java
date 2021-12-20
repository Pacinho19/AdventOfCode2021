package pl.pacinho.adventofcode2021.challange.day20;

import javafx.util.Pair;
import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day20Part1 implements CalculateI {

    private String algorithm;

    //5141> - <5624
    //5285?
    public static void main(String[] args) {
        System.out.println(new Day20Part1().calculate("day20\\input.txt"));
    }

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

        Character[][] characters = cloneArray(chars);

        List<Pair<Integer, Integer>> pairs = checkMaxDim(characters);
        Pair<Integer, Integer> min = pairs.get(0);
        Pair<Integer, Integer> max = pairs.get(1);

        for (int row = 0; row < characters.length; row++) {
            if(row<min.getValue() || row>max.getValue()) continue;

            for (int col = 0; col < characters[row].length; col++) {
                if(col<min.getKey() || col>max.getKey()) continue;

                List<Character> neighbors = getNeighbors(row, col, characters);
                Character character = checkNewValue(neighbors);
                chars[row][col] = character;
            }
        }
        printArray(chars);
    }

    private List<Pair<Integer, Integer>> checkMaxDim(Character[][] characters) {
        int maxRow = 0;
        int maxCol = 0;
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        for (int row = 0; row < characters.length; row++) {
            for (int col = 0; col < characters[row].length; col++) {
                if (characters[row][col] == '#') {
                    if (row > maxRow) maxRow = row;
                    if (col > maxCol) maxCol = col;

                    if (row < minRow) minRow = row;
                    if (col < minCol) minCol = col;
                }
            }
        }
        return Arrays.asList(new Pair<>(minCol-1, minRow-1), new Pair<>(maxCol+1, maxRow+1));
    }

    private Character[][] cloneArray(Character[][] chars) {
        Character[][] characters = new Character[chars.length][chars[0].length];
        for (int row = 0; row < chars.length; row++)
            System.arraycopy(chars[row], 0, characters[row], 0, chars[row].length);
        return characters;
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
        int decimal = getDecimal(binary);
        return algorithm.charAt(decimal);
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

        return out;
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