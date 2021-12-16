package pl.pacinho.adventofcode2021.challange.day16;

import junit.framework.TestCase;

public class Day16Part2Test extends TestCase {

    public void testCalculate() {
        assertEquals(3, new Day16Part2().calculate("day16\\inputExample5.txt"));
    }

    public void testCalculate2() {
        assertEquals(54, new Day16Part2().calculate("day16\\inputExample6.txt"));
    }

    public void testCalculate3() {
        assertEquals(7, new Day16Part2().calculate("day16\\inputExample7.txt"));
    }

    public void testCalculate4() {
        assertEquals(1, new Day16Part2().calculate("day16\\inputExample8.txt"));
    }

    public void testCalculate5() {
        assertEquals(0, new Day16Part2().calculate("day16\\inputExample9.txt"));
    }

    public void testCalculate6() {
        assertEquals(0, new Day16Part2().calculate("day16\\inputExample10.txt"));
    }

    public void testCalculate7() {
        assertEquals(9, new Day16Part2().calculate("day16\\inputExample11.txt"));
    }

    public void testCalculate8() {
        assertEquals(1, new Day16Part2().calculate("day16\\inputExample12.txt"));
    }
}