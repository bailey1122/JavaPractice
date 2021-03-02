package multithreading;

import org.junit.platform.commons.logging.LoggerFactory;

import java.util.logging.Logger;

public class EventLoggingTask implements Runnable {
    private Logger logger = (Logger) LoggerFactory.getLogger(EventLoggingTask.class);

    // the thread will just read a message from the queue and log it in a log file
    @Override
    public void run() {
        logger.info("Message");
    }
}
