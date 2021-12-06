package pl.pacinho.adventofcode2021.challange.day6;

import junit.framework.TestCase;

public class Day6Part1Test extends TestCase {

    public void testCalculate() {
        assertEquals(26, new Day6Part1(18).calculate("day6\\inputExample.txt"));
    }

    public void testCalculate2() {
        assertEquals(5934, new Day6Part1(80).calculate("day6\\inputExample.txt"));
    }
}