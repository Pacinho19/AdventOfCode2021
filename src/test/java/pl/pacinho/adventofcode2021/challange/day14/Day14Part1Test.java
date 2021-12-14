package pl.pacinho.adventofcode2021.challange.day14;

import junit.framework.TestCase;
import pl.pacinho.adventofcode2021.challange.day13.Day13Part1;

public class Day14Part1Test extends TestCase {

    public void testCalculate() {
        assertEquals(1588, new Day14Part1().calculate("day14\\inputExample.txt"));
    }
}