package ru.innopolis.course3.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Danil Popov course-3.
 */
public class CounterValidatorTest {

    private CounterValidator counterValidator;

    @Before
    public void initThread() {
        counterValidator = new CounterValidatorImpl();
    }

    @Test
    public void threadValidateTest() {
        assertTrue(counterValidator.validateInt(4));
        assertFalse(counterValidator.validateInt(5));
        assertFalse(counterValidator.validateInt(-4));
    }
}
