package ru.innopolis.course3.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Danil Popov course-3.
 */
public class Main implements ThreadInterrupter, IntegerAdder {

    private List<Thread> threads = new ArrayList<>();
    private AtomicLong count = new AtomicLong(0);
    private CounterValidator counterValidator;

    public static void main(String[] args) {
        new Main(new CounterValidatorImpl()).createThreads();
    }

    public Main(CounterValidator counterValidator) {
        this.counterValidator = counterValidator;
    }

    @Override
    public void addInt(int i) {
        if (counterValidator.validateInt(i)) {
            count.addAndGet(i);
            System.out.println(count);
        }
    }

    @Override
    public synchronized void interruptAll() {
        for(Thread t: threads) {
            t.interrupt();
        }
    }

    @Override
    public void interruptAll(String msg) {
        interruptAll();
        System.out.println(msg);
    }

    private void createThreads() {
        List<String> files = getResourcesList();
        for (int i = 0; i < files.size(); i++) {
            Thread t1 = new CounterThread(this, this, files.get(i));
            threads.add(t1);
            t1.start();
        }
    }

    private List<String> getResourcesList() {
        List<String> files = new ArrayList<>();
        files.add(System.getProperty("user.dir") + "/src/main/resources/" + "file1.txt");
        files.add(System.getProperty("user.dir") + "/src/main/resources/" + "file2.txt");
        files.add(System.getProperty("user.dir") + "/src/main/resources/" + "file3.txt");
        return files;
    }
}
