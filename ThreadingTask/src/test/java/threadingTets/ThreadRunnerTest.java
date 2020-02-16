package threadingTets;

import org.junit.Test;
import threading.ThreadRunner;

import static org.junit.Assert.*;

public class ThreadRunnerTest {

    @Test
    public void runTest() throws InterruptedException {
        int time = 700;
        Thread thread = new Thread(new ThreadRunner(time));
        thread.start();
        assertTrue(thread.isAlive());
        Thread.sleep(time*2);
        assertFalse(thread.isAlive());
    }
}