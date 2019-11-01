import network.client.CustomLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestCustomLogger {
    @Mock
    private Logger logger;

    private CustomLogger customLogger;

    @Before
    public void setUp() {
        customLogger = new CustomLogger(TestCustomLogger.class.getName());
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkLogging1() {
        String testMsg = "Test Message";
        logger = mock(Logger.class);
        customLogger.setLogger(logger);
        doNothing().when(logger).info((String) any());
        customLogger.logMessage(testMsg);
        verify(logger, times(1)).info(testMsg);
    }

    @Test
    public void checkLogging2() {
        String testMsg = "Test Message";
        logger = mock(Logger.class);
        customLogger.setLogger(logger);
        Level l = Level.SEVERE;
        IOException e = new IOException();
        doNothing().when(logger).log((Level) any(), (String) any(), (Throwable) any());
        customLogger.logMessage(l, testMsg, e);
        verify(logger, times(1)).log(l, testMsg, e);
    }

    @Test
    public void GetSetCheck() {
        logger = Logger.getLogger("MyLogger");
        customLogger.setLogger(logger);
        Assert.assertEquals(logger, customLogger.getLogger());
    }
}