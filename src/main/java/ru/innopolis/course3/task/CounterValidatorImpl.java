package ru.innopolis.course3.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Popov Danil
 *
 * Validates string
 */
public class CounterValidatorImpl implements CounterValidator {
    private static final String NUMBER_FORMAT_MSG = "Program has stopped, file format is incorrect";

    private Logger logger = LoggerFactory.getLogger(CounterValidatorImpl.class);
    private ThreadInterrupter interrupter;

    /**
     *
     * @param interrupter can check is threads interrupted and
     *                    can set interrupt-flag to true
     */
    public CounterValidatorImpl(ThreadInterrupter interrupter) {
        this.interrupter = interrupter;
    }

    /**
     * Validation method for positive even integers
     *
     * @param str string with integer value
     * @return true if str contains allowable integer value
     */
    @Override
    public boolean validateInt(String str) {
        int i = -1;
        Pattern p = Pattern.compile(".*[^0-9\\-].*");
        Matcher m = p.matcher(str);
        boolean match = m.matches();
        if (!match) {
            i = Integer.parseInt(str);
        } else {
            logger.error("Number: " + str);
            interrupter.interruptAll(NUMBER_FORMAT_MSG);
        }
        return i >= 0 && i % 2 == 0;
    }
}
