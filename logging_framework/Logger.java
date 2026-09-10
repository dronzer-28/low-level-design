package logging_framework;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import logging_framework.enums.LogLevel;

public class Logger {
    private final String loggerName;
    private final LogDispatcher dispatcher;
    private LogLevel minLevel;
    private List<Destination> destinations;

    public Logger(String loggerName, LogDispatcher dispatcher) {
        this.loggerName = loggerName;
        this.dispatcher = dispatcher;
        destinations = new ArrayList<>();
        minLevel = LogLevel.ERROR;
    }

    public void setMinLogLevel(LogLevel level){
        this.minLevel = level;
    }

    public void setDestinations(List<Destination> destinations){
        for(Destination dest: destinations){
            this.destinations.add(dest);
        }
    }

    public List<Destination> getDestinations(){
        return destinations;
    }

    public void log(LogLevel level, String message){
        if(level.isGreaterOrEqual(minLevel)){
            LogRecord record = new LogRecord(level, loggerName, message, Thread.currentThread().getName(), LocalDateTime.now());
            dispatcher.dispatch(record, destinations);
        }
    }

    public void debug(String message){
        log(LogLevel.DEBUG, message);
    }

    public void info(String message){
        log(LogLevel.INFO, message);
    }

    public void warn(String message){
        log(LogLevel.WARN, message);
    }

    public void error(String message){
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message){
        log(LogLevel.FATAL, message);
    }
}
