package pl.pacinho.adventofcode2021.challange.day1;

import junit.framework.TestCase;
import org.junit.Test;

public class Day1Part1Test extends TestCase {

    @Test
    public void testCalculate() {
        assertEquals(7, new Day1Part1().calculate("day1\\inputExample.txt"));
    }
}