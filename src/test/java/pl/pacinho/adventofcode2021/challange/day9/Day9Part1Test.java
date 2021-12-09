package pl.pacinho.adventofcode2021.challange.day9;

import junit.framework.TestCase;
import pl.pacinho.adventofcode2021.challange.day8.Day8Part1;

public class Day9Part1Test extends TestCase {

    public void testCalculate() {
        assertEquals(15, new Day9Part1().calculate("day9\\inputExample.txt"));
    }
}