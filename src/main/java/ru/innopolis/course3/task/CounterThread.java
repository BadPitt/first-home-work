package ru.innopolis.course3.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.text.SimpleDateFormat;

/**
 * Created by Danil Popov course-3.
 */
public class CounterThread extends Thread {

    private Logger logger = LoggerFactory.getLogger(CounterThread.class);

    private final String fileName;
    private ThreadsFileReader threadsFileReader;
    private Parser parser;

    public CounterThread(ThreadInterrupter interrupter, Summator adder , String fn) {
        fileName = fn;
        threadsFileReader = new ThreadsFileReaderImpl(interrupter);
        parser = new IntParser(interrupter, adder, new CounterValidatorImpl(interrupter));
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        logger.debug("Thread has started: {} in {}",
                Thread.currentThread().getName(),
                sdf.format(System.currentTimeMillis()));

        MDC.put("File name", fileName);

        String str = threadsFileReader.read(fileName);
        parser.parseString(str);

        MDC.clear();

        logger.debug("Thread has stopped: {} in {}",
                Thread.currentThread().getName(),
                sdf.format(System.currentTimeMillis()));
    }
}
