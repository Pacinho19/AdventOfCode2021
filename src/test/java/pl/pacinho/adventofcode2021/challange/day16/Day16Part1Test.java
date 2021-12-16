package pl.pacinho.adventofcode2021.challange.day16;

import junit.framework.TestCase;

public class Day16Part1Test extends TestCase {

    public void testCalculate() {
        assertEquals(16, new Day16Part1().calculate("day16\\inputExample.txt"));
    }

    public void testCalculate2() {
        assertEquals(12, new Day16Part1().calculate("day16\\inputExample2.txt"));
    }

    public void testCalculate3() {
        assertEquals(23, new Day16Part1().calculate("day16\\inputExample3.txt"));
    }

    public void testCalculate4() {
        assertEquals(31, new Day16Part1().calculate("day16\\inputExample4.txt"));
    }

}