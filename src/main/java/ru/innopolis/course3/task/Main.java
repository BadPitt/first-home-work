package ru.innopolis.course3.task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Popov Danil
 */
public class Main {

    public static void main(String[] args) {
        new ThreadGenerator(getResourcesList()).createThreads();
    }

    private static List<String> getResourcesList() {
        List<String> files = new ArrayList<>();
        files.add(System.getProperty("user.dir") + "/src/main/resources/" + "file1.txt");
        files.add(System.getProperty("user.dir") + "/src/main/resources/" + "file2.txt");
        files.add(System.getProperty("user.dir") + "/src/main/resources/" + "file3.txt");
        return files;
    }
}
