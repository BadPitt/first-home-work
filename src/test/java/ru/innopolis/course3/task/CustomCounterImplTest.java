package ru.innopolis.course3.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Danil Popov course-3.
 */
public class CustomCounterImplTest {

    private int[] sum;
    private IntegerAdder adder;
    private CustomCounter counter;

    @Before
    public void init() {
        sum = new int[]{0};
        adder = new IntegerAdder() {
            @Override
            public void addInt(int i) {
                sum[0] += i;
            }
        };
        counter = new CustomCounterImpl(new ThreadInterrupter() {
            @Override
            public void interruptAll() {
                throw new NumberFormatException();
            }

            @Override
            public void interruptAll(String msg) {
                interruptAll();
            }
        }, adder);
    }

    @Test
    public void countTest() {
        counter.count("1 2 -1 4 2");
        assertEquals(8, sum[0]);
    }

    @Test(expected = NumberFormatException.class)
    public void countWrongTest() {
        counter.count("1 2 -1 4 af2");
    }
}
