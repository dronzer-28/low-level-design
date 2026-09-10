package logging_framework;

interface Destination {
    public void setFormatter(Formatter formatter);
    public void append(LogRecord logRecord);
    public void close();
}