import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ThreadGroupInfoPrinterTest {

    private int firstTimeUnit = 1000;
    private int secondTimeUnit = 2000;

    @Test
    public void checkDefaultValues() throws NoSuchFieldException, IllegalAccessException {
        ThreadGroupInfoPrinter threadLogger = new ThreadGroupInfoPrinter();
        Field field = ThreadGroupInfoPrinter.class.
                getDeclaredField("WaitTime");
        field.setAccessible(true);
        int waitTime = (int) field.get(threadLogger);
        Assert.assertEquals(5000,waitTime);
    }
    @Test
    public void numberOfThreadsInGroupTest() throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup(Thread.currentThread().getThreadGroup(), "firstGroup");
        ThreadGroup group2 = new ThreadGroup(Thread.currentThread().getThreadGroup(), "secondGroup");
        (new Thread(group1, new ThreadRunner(firstTimeUnit))).start();
        (new Thread(group1, new ThreadRunner(firstTimeUnit))).start();
        (new Thread(group2, new ThreadRunner(firstTimeUnit))).start();

        (new Thread(group1, new ThreadRunner(secondTimeUnit))).start();
        (new Thread(group2, new ThreadRunner(secondTimeUnit))).start();

        Assert.assertEquals(3,group1.activeCount());
        Assert.assertEquals(2,group2.activeCount());

        Thread.sleep(secondTimeUnit);

        Assert.assertEquals(1,group1.activeCount());
        Assert.assertEquals(1,group2.activeCount());

    }
}