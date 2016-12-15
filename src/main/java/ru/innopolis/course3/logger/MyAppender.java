package ru.innopolis.course3.logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by Danil Popov course-3.
 */
public class MyAppender extends AppenderSkeleton {

    //protected String fileName;
    //protected Layout layout;

    /*public MyAppender(String fileName, Layout layout) {
        this.fileName = fileName;
        this.layout = layout;
    }*/

    @Override
    protected void append(LoggingEvent event) {
        //doAppend(event);
        System.out.println(event.getLevel() + " - " + event.getRenderedMessage());
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
