package ru.innopolis.course3.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Popov Danil
 *
 * Read string from file, according to MAX_FILE_SIZE
 */
public class ThreadsFileReaderImpl implements ThreadsFileReader {

    private static final String FILE_NOT_FOUND_MSG = "Program has stopped, file not found";
    private static final String IO_ERROR_MSG = "Program has stopped, something went wrong";
    private static final String FILE_IS_TOO_BIG_MSG = "Program has stopped, file is too big";

    private final int MAX_FILE_SIZE;

    private Logger logger = LoggerFactory.getLogger(ThreadsFileReaderImpl.class);
    private ThreadInterrupter interrupter;

    /**
     * Create new ThreadsFileReaderImpl instance
     * with default MAX_FILE_SIZE = 8192
     *
     * @param interrupter Thread interrupter which can check
     *                    is threads interrupted or not.
     *                    And it can set interrupt-flag to true
     *                    for interrupting other threads
     */
    public ThreadsFileReaderImpl(ThreadInterrupter interrupter) {
        this(interrupter, 8192);
    }

    /**
     * @param interrupter Thread interrupter which can check
     *                    is threads interrupted or not.
     *                    And it can set interrupt-flag to true
     *                    for interrupting other threads
     * @param maxFileSize Max count of symbols in file
     */
    public ThreadsFileReaderImpl(ThreadInterrupter interrupter, int maxFileSize) {
        this.interrupter = interrupter;
        MAX_FILE_SIZE = maxFileSize;
    }

    /**
     * @param fileName Full file name
     * @return String with file content
     */
    @Override
    public String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            char[] charArray = new char[512];
            int i = bufferedReader.read(charArray);

            while (i > 0) {
                stringBuilder.append(charArray, 0, i);
                if (stringBuilder.capacity() > MAX_FILE_SIZE) {
                    logger.error("FileName: " + fileName);
                    interrupter.interruptAll(FILE_IS_TOO_BIG_MSG + ": " + fileName);
                    break;
                }
                i = bufferedReader.read(charArray);
            }

        } catch (FileNotFoundException e) {
            logger.error("FileName: " + fileName, e);
            interrupter.interruptAll(FILE_NOT_FOUND_MSG + ": " + fileName);
        } catch (IOException e) {
            logger.error("FileName: " + fileName, e);
            interrupter.interruptAll(IO_ERROR_MSG);
        }

        return stringBuilder.toString();
    }
}
