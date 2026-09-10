package logging_framework;

import java.time.LocalDateTime;
import logging_framework.enums.LogLevel;

public class LogRecord {
    private final LogLevel logLevel;
    private final String loggerName;
    private final String message;
    private final String threadName;
    private final LocalDateTime timeStamp;

    public LogRecord(LogLevel logLevel, String loggerName, String message, String threadName, LocalDateTime timeStamp) {
        this.logLevel = logLevel;
        this.loggerName = loggerName;
        this.message = message;
        this.threadName = threadName;
        this.timeStamp = timeStamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
