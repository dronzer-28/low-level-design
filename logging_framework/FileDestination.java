package logging_framework;

import java.io.FileWriter;
import java.io.IOException;

public class FileDestination implements Destination{
    private Formatter formatter;
    private FileWriter writer;

    public FileDestination(String filePath){
        formatter = new PlainTextFormatter();
        try{
            writer = new FileWriter(filePath, true);
        } catch (IOException e){
            System.out.println("Failed to create writer for file logs: "+ e.getMessage());
        }
    }

    @Override
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogRecord logRecord) {
        try {
            writer.write(formatter.format(logRecord));
        } catch (IOException e) {
            System.out.println("Failed to write logs to file: "+ e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to close logs file: "+ e.getMessage());
        }
    }
    
}
