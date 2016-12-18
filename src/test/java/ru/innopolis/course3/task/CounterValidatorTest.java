package ru.innopolis.course3.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Popov Danil
 */
public class CounterValidatorTest {

    private CounterValidator counterValidator;

    @Before
    public void initThread() {
        ThreadInterrupter interrupter = new ThreadInterrupter() {
            @Override
            public boolean isThreadInterrupted() {
                return false;
            }

            @Override
            public void interruptAll() {

            }

            @Override
            public void interruptAll(String msg) {
                interruptAll();
            }
        };
        counterValidator = new CounterValidatorImpl(interrupter);
    }

    @Test
    public void threadValidateTest() {
        assertTrue(counterValidator.validateInt("4"));
        assertFalse(counterValidator.validateInt("5"));
        assertFalse(counterValidator.validateInt("-4"));
        assertFalse(counterValidator.validateInt("-a4"));
        assertFalse(counterValidator.validateInt(" 4"));
    }
}
