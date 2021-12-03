package pl.pacinho.adventofcode2021.challange.day3;

public class DecimalParser {

    public static int parseFromBinary(String binary) {
        int currPos = 0;
        int sum = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            sum += Math.pow(2, currPos) * Character.getNumericValue(binary.charAt(i));
            currPos++;
        }
        return sum;
    }
}
