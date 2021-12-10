package pl.pacinho.adventofcode2021.challange.day10;

import junit.framework.TestCase;
import pl.pacinho.adventofcode2021.challange.day9.Day9Part1;

public class Day10Part1Test extends TestCase {

    public void testCalculate() {
        assertEquals(26397, new Day10Part1().calculate("day10\\inputExample.txt"));
    }
}