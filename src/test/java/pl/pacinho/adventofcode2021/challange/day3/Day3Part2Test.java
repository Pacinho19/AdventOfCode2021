package pl.pacinho.adventofcode2021.challange.day3;

import junit.framework.TestCase;
import pl.pacinho.adventofcode2021.challange.day2.Day2Part2;

public class Day3Part2Test extends TestCase {

    public void testCalculate() {
        assertEquals(230, new Day3Part2().calculate("day3\\inputExample.txt"));
    }
}