package pl.pacinho.adventofcode2021.challange.day12;

import junit.framework.TestCase;
import pl.pacinho.adventofcode2021.challange.day11.Day11Part1;

public class Day12Part2Test extends TestCase {

    public void testCalculate() {assertEquals(36, new Day12Part2().calculate("day12\\inputExample.txt")); }

    public void testCalculate2() {
        assertEquals(103, new Day12Part2().calculate("day12\\inputExample2.txt"));
    }

    public void testCalculate3() {
        assertEquals(3509, new Day12Part2().calculate("day12\\inputExample3.txt"));
    }

}