package ru.innopolis.course3.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Danil Popov course-3.
 */
public class CustomCounterImpl implements CustomCounter {

    private static final String NUMBER_FORMAT_MSG = "Program has stopped, file format is incorrect";

    private Logger logger = LoggerFactory.getLogger(CustomCounterImpl.class);
    private IntegerAdder integerAdder;
    private ThreadInterrupter interrupter;

    public CustomCounterImpl(ThreadInterrupter interrupter, IntegerAdder integerAdder) {
        this.interrupter = interrupter;
        this.integerAdder = integerAdder;
    }

    @Override
    public void count(String s) {
        String[] array = s.split(" ");
        for (String str : array) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            try {
                integerAdder.addInt(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                logger.error("Number: " + str, e);
                interrupter.interruptAll(NUMBER_FORMAT_MSG);
            }
        }
    }
}
