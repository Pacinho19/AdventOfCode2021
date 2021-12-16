package pl.pacinho.adventofcode2021.challange.day16;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Day16Part1 implements CalculateI {

    private Long sum = 0L;

    public static void main(String[] args) {
        System.out.println(new Day16Part1().calculate("day16\\input.txt"));
    }

    private static HashMap<String, String> bitMap = new HashMap<String, String>() {
        {
            put("0", "0000");
            put("1", "0001");
            put("2", "0010");
            put("3", "0011");
            put("4", "0100");
            put("5", "0101");
            put("6", "0110");
            put("7", "0111");
            put("8", "1000");
            put("9", "1001");
            put("A", "1010");
            put("B", "1011");
            put("C", "1100");
            put("D", "1101");
            put("E", "1110");
            put("F", "1111");
        }
    };

    @Override
    public long calculate(String filePath) {
        String bits = Arrays.stream(FileUtils.readAsText(new File(filePath))
                        .split(""))
                .map(bitMap::get)
                .collect(Collectors.joining(""));

        checkBits(bits);
        return sum;
    }

    private void checkBits(String bits) {
        if (bits.length() < 11) return;

        int version = getDecimal(bits.substring(0, 3));
        sum += version;

        int typeId = getDecimal(bits.substring(3, 6));
        if (typeId == 4) checkTypeId4(bits.substring(6));
        else checkTypeOtherId(bits.substring(6));
    }

    private void checkTypeOtherId(String bits) {
        int digitAtPos = getDigitAtPos(bits, 0);
        checkBits(bits.substring(digitAtPos == 1 ? 12 : 16));
    }

    private void checkTypeId4(String bits) {
        boolean last = false;
        int pos = 0;
        while (!last) {
            int digitAtPos = getDigitAtPos(bits, pos);
            pos += 5;
            last = digitAtPos == 0;
        }
        checkBits(bits.substring(pos));
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
        return Integer.parseInt(String.valueOf(bits.charAt(pos)));
    }

}