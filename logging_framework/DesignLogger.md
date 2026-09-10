# Requirements

- support log severity levels: DEBUG, INFO, WARN, ERROR, FATAL
- support multiple output destinations like console and file, a log record can be sent to multiple destinations simultaneously
- filter log messages based on min log level
- support async logging to prevent blocking main thread
- allow client applications to configure logger with log level, formatter and desitnations
- concurrent calls are safe. a record's bytes never interleave with another on the same destination

# Core Entities

enum LogLevel
Destination
LogRecord
Formatter
Logger
LogDispatcher
LogManager

# Class Design and Relationships

enum LogLevel{
    DEBUG, 
    INFO, 
    WARN, 
    ERROR, 
    FATAL
}

class LogRecord{
    - logLevel: LogLevel
    - loggerName: String
    - message: String
    - timeStamp: LocalDateTime
}

class LogManager{
    - loggers: Map<String, Logger>
    - dispatcher: LogDispatcher
    + getLogger(loggerName: String) -> Logger
}

interface Destination{
    - formatter: Formatter
}

class ConsoleDestination implements Destination{

}

class FileDestination implements Destination{
    
}

class Logger{
    - dispatcher: Dispatcher
    - loggerName: String
    - minLevel: LogLevel
    - destinations: List<Destination>

    + Logger(loggerName: String, dispatcher: Dispatcher)
    + setMinLogLevel(logLevel: LogLevel) -> void
    + setDestinations(destinations: List<Destinations>) -> void
    + debug(message: String) -> void
}