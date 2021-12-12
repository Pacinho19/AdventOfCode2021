package pl.pacinho.adventofcode2021.challange.day12;

import junit.framework.TestCase;

public class Day12Part1Test extends TestCase {

    public void testCalculate() {
        assertEquals(10, new Day12Part1().calculate("day12\\inputExample.txt"));
    }

    public void testCalculate2() {
        assertEquals(19, new Day12Part1().calculate("day12\\inputExample2.txt"));
    }

    public void testCalculate3() {
        assertEquals(226, new Day12Part1().calculate("day12\\inputExample3.txt"));
    }

}