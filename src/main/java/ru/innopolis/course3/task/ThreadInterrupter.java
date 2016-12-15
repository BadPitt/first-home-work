package ru.innopolis.course3.task;

/**
 * Created by Danil Popov course-3.
 */
public interface ThreadInterrupter {
    void interruptAll();
    void interruptAll(String msg);
}
