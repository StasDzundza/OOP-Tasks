import java.util.logging.Level;
import java.util.logging.Logger;
public class ThreadRunner implements Runnable{
    private int time;
    private final static Logger LOGGER = Logger.getLogger(ThreadRunner.class.getName());

    ThreadRunner(int time) {
        this.time=time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE,e.getMessage());
        }
    }
}
