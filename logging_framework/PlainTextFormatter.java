package logging_framework;

public class PlainTextFormatter implements Formatter {

    @Override
    public String format(LogRecord logRecord) {
        String outputMessage = String.format(
                "%s [%s] %s - %s: %s ",
                logRecord.getTimeStamp().toString(),
                logRecord.getThreadName(),
                logRecord.getLogLevel(),
                logRecord.getLoggerName(),
                logRecord.getMessage());
        return outputMessage;
    }

}
