package ru.innopolis.course3.task;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/**
 * @author Popov Danil
 */
public class ThreadGeneratorTest {

    private Summator adder;

    @Before
    public void init() {
        adder = new ThreadGenerator(new ArrayList<>());
    }

    @Test
    public void addIntTest() throws NoSuchFieldException, IllegalAccessException {
        adder.addInt(4);
        adder.addInt(6);
        adder.addInt(1);
        Field f = adder.getClass().getDeclaredField("count");
        f.setAccessible(true);
        assertEquals(11, ((AtomicLong)f.get(adder)).get());
    }
}
