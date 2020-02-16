package threading;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadGroupInfoPrinter {
    private final static int WaitTime = 5000;
    private final static Logger LOGGER = Logger.getLogger(ThreadGroupInfoPrinter.class.getName());

    public static void printThreadsInfo(ThreadGroup group) {
        Runnable log = () -> {
            while (group.activeCount() > 0) {
                Thread[] threads = new Thread[group.activeCount()];
                int count = group.enumerate(threads);
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    str.append(threads[i]);
                    str.append("\n");
                }
                System.out.println("Group " + group.getName() + ";\n" +
                        "Threads: " + str.toString() + "\n");

                try {
                    Thread.sleep(WaitTime);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE,e.getMessage());
                }
            }
        };
        Thread logThread = new Thread(log);
        logThread.start();
    }
}
