package logging_framework;

public class ConsoleDestination implements Destination{

    private Formatter formatter;

    public ConsoleDestination(){
        this.formatter = new PlainTextFormatter();
    }

    @Override
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogRecord logRecord) {
        System.out.println(formatter.format(logRecord));
    }

    @Override
    public void close() {
        
    }
    
}
