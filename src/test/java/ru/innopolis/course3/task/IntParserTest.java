package ru.innopolis.course3.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Popov Danil
 */
public class IntParserTest {

    private int[] sum;
    private boolean threadInterrupt;
    private Summator adder;
    private Parser counter;
    private ThreadInterrupter interrupter;

    @Before
    public void init() {
        sum = new int[]{0};
        adder = new Summator() {
            @Override
            public void addInt(int i) {
                sum[0] += i;
            }
        };
        interrupter = new ThreadInterrupter() {
            @Override
            public boolean isThreadInterrupted() {
                return threadInterrupt;
            }

            @Override
            public void interruptAll() {
                threadInterrupt = true;
            }

            @Override
            public void interruptAll(String msg) {
                interruptAll();
            }
        };
        counter = new IntParser(interrupter, adder,
                new CounterValidatorImpl(interrupter));
    }

    @Test
    public void countTest() {
        counter.parseString("1 2 -1 4 2");
        assertEquals(8, sum[0]);
    }

    @Test
    public void countWrongTest() {
        counter.parseString("1 2 -1 4 af2");
        assertTrue(threadInterrupt);
    }
}
