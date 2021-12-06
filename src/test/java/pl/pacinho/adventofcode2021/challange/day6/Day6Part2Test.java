package pl.pacinho.adventofcode2021.challange.day6;

import junit.framework.TestCase;

public class Day6Part2Test extends TestCase {

    public void testCalculate() {
        assertEquals(26, new Day6Part2(18).calculate("day6\\inputExample.txt"));
    }

    public void testCalculate2() {
        assertEquals(5934, new Day6Part2(80).calculate("day6\\inputExample.txt"));
    }

    public void testCalculate3() {
        assertEquals(26984457539L, new Day6Part2(256).calculate("day6\\inputExample.txt"));
    }
}