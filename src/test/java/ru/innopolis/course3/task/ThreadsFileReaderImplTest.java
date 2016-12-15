package ru.innopolis.course3.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Danil Popov course-3.
 */
public class ThreadsFileReaderImplTest {
    private ThreadsFileReader threadsFileReader;

    @Before
    public void init() {
        ThreadInterrupter interrupter = mock(ThreadInterrupter.class);
        threadsFileReader = new ThreadsFileReaderImpl(interrupter);
    }

    @Test
    public void testReader() {
        String str = threadsFileReader.read(System.getProperty("user.dir") + "/src/test/resources/" + "file_test.txt");
        assertTrue("10".equals(str));
    }
}
