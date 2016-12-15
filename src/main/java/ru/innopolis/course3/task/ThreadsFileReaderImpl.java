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

    private Logger logger = LoggerFactory.getLogger(ThreadsFileReaderImpl.class);
    private ThreadInterrupter interrupter;

    public ThreadsFileReaderImpl(ThreadInterrupter interrupter) {
        this.interrupter = interrupter;
    }

    @Override
    public String read(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            while (bufferedReader.ready()) {
                stringBuffer.append(bufferedReader.readLine());
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
