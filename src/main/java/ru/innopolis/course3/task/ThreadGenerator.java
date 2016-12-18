package ru.innopolis.course3.task;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Popov Danil
 *
 * Generates threads and contains
 * interrupt-flag and share sum
 */
public class ThreadGenerator implements ThreadInterrupter, Summator {
    private AtomicLong count = new AtomicLong(0);
    private volatile boolean isThreadStopped = false;
    private List<String> files;

    /**
     * @param resources list of resources full name
     */
    public ThreadGenerator(List<String> resources) {
        this.files = resources;
    }

    /**
     * Adding new element to share sum
     *
     * @param i new element, which will add to share sum
     */
    @Override
    public void addInt(int i) {
        count.addAndGet(i);
        System.out.println(count);
    }

    /**
     * Checks share interrupt-flag
     *
     * @return true if thread have to stop
     */
    @Override
    public boolean isThreadInterrupted() {
        return isThreadStopped;
    }

    /**
     * Sets share interrupt-flag to true
     */
    @Override
    public void interruptAll() {
        isThreadStopped = true;
    }

    /**
     * Sets share interrupt-flag to true
     * and show massage to System.out
     *
     * @param msg reason of interrupting
     */
    @Override
    public void interruptAll(String msg) {
        interruptAll();
        System.out.println(msg);
    }

    /**
     * Creates and starts threads for every resource
     * from resource list
     */
    public void createThreads() {
        for (int i = 0; i < files.size(); i++) {
            Thread t1 = new CounterThread(this, this, files.get(i));
            t1.start();
        }
    }
}
