package pl.pacinho.adventofcode2021.challange.day2;

import junit.framework.TestCase;
import org.junit.Test;

public class Day2Part1Test extends TestCase {

    @Test
    public void testCalculate() {
        assertEquals("Good !", 150, new Day2Part1().calculate("day2\\inputExample.txt"));
    }
}