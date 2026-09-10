package logging_framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    private static final LogManager INSTANCE = new LogManager();
    private final Map<String, Logger> loggers = new ConcurrentHashMap<>();
    private final LogDispatcher dispatcher;

    private LogManager(){
        dispatcher = new LogDispatcher();
    }

    public static LogManager getInstance(){
        return  INSTANCE;
    }

    public Logger getLogger(String loggerName){
        return loggers.computeIfAbsent(loggerName, name -> new Logger(loggerName, dispatcher));
    }

    public LogDispatcher getDispatcher(){
        return dispatcher;
    }

    public void shutDown(){
        dispatcher.close();
        loggers.values().stream()
            .flatMap(logger-> logger.getDestinations().stream())
            .distinct()
            .forEach(dest -> dest.close());
        System.out.println("Logging framework shut down");
    }
}
