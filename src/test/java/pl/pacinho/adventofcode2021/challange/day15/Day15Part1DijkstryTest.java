package pl.pacinho.adventofcode2021.challange.day15;

import junit.framework.TestCase;

public class Day15Part1DijkstryTest extends TestCase {

    public void testCalculate() {
        assertEquals(40, new Day15Part1Dijkstry().calculate("day15\\inputExample.txt"));
    }

}