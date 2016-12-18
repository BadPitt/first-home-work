package ru.innopolis.course3.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Danil Popov course-3.
 */
public class ThreadsFileReaderImpl implements ThreadsFileReader {

    private static final String FILE_NOT_FOUND_MSG = "Program has stopped, file not found";
    private static final String IO_ERROR_MSG = "Program has stopped, something went wrong";
    private static final String FILE_IS_TOO_BIG_MSG = "Program has stopped, file is too big";

    private final int MAX_FILE_SIZE;

    private Logger logger = LoggerFactory.getLogger(ThreadsFileReaderImpl.class);
    private ThreadInterrupter interrupter;

    public ThreadsFileReaderImpl(ThreadInterrupter interrupter) {
        this(interrupter, 8192);
    }

    public ThreadsFileReaderImpl(ThreadInterrupter interrupter, int maxFileSize) {
        this.interrupter = interrupter;
        MAX_FILE_SIZE = maxFileSize;
    }

    @Override
    public String read(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            char[] charArray = new char[512];
            int i = bufferedReader.read(charArray);

            while (i > 0) {
                stringBuffer.append(charArray, 0, i);
                if (stringBuffer.capacity() > MAX_FILE_SIZE) {
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

        return stringBuffer.toString();
    }
}
