package ru.innopolis.course3.task;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Danil Popov course-3.
 */
public class ThreadGenerator implements ThreadInterrupter, Summator {
    private AtomicLong count = new AtomicLong(0);
    private volatile boolean isThreadStopped = false;
    private List<String> files;

    public ThreadGenerator(List<String> resources) {
        this.files = resources;
    }

    @Override
    public void addInt(int i) {
        count.addAndGet(i);
        System.out.println(count);
    }

    @Override
    public boolean isThreadInterrupted() {
        return isThreadStopped;
    }

    @Override
    public void interruptAll() {
        isThreadStopped = true;
    }

    @Override
    public void interruptAll(String msg) {
        interruptAll();
        System.out.println(msg);
    }

    public void createThreads() {
        for (int i = 0; i < files.size(); i++) {
            Thread t1 = new CounterThread(this, this, files.get(i));
            t1.start();
        }
    }
}
