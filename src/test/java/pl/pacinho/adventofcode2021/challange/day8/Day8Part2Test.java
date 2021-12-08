package pl.pacinho.adventofcode2021.challange.day8;

import junit.framework.TestCase;
import pl.pacinho.adventofcode2021.challange.day7.Day7Part1;

public class Day8Part2Test extends TestCase {

    public void testCalculate() {
        assertEquals(61229, new Day8Part2().calculate("day8\\inputExample.txt"));
    }

    public void testCalculate2() {
        assertEquals(5353, new Day8Part2().calculate("day8\\inputExample2.txt"));
    }

}