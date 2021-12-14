package pl.pacinho.adventofcode2021.challange.day14;

import junit.framework.TestCase;

public class Day14Part2Test extends TestCase {

    public void testCalculate() {
        assertEquals(2188189693529L, new Day14Part2(40).calculate("day14\\inputExample.txt"));
    }

    public void testCalculate2() {
        assertEquals(1588, new Day14Part2(10).calculate("day14\\inputExample.txt"));
    }

}