package network.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private Logger log;

    public CustomLogger(String className){
        log = Logger.getLogger(className);
    }

    public void logMessage(String message){
        log.info(message);
    }

    public void logMessage(Level level, String message, IOException e){
        log.log(level,message,e);
    }


}
