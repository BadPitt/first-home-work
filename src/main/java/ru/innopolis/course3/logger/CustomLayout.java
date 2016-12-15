package ru.innopolis.course3.logger;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by Danil Popov course-3.
 */
public class CustomLayout extends Layout {

    StringBuffer sb = new StringBuffer(128);

    @Override
    public String format(LoggingEvent event) {
        sb.setLength(0);
        sb.append(event.getLevel().toString());
        sb.append(" - ");
        sb.append(event.getRenderedMessage());
        sb.append(LINE_SEP);
        return sb.toString();
    }

    @Override
    public boolean ignoresThrowable() {
        return true;
    }

    @Override
    public void activateOptions() {

    }
}
