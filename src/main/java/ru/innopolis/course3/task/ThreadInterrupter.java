package ru.innopolis.course3.task;

/**
 * @author Popov Danil
 */
public interface ThreadInterrupter {
    boolean isThreadInterrupted();
    void interruptAll();
    void interruptAll(String msg);
}
