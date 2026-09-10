package logging_framework;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import logging_framework.enums.LogLevel;

import java.time.*;
import java.time.format.*;
import java.io.FileWriter;
import java.io.IOException;

public class LoggingFrameworkDemo {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getInstance();

        // --- 1. Configure a logger and log at its level ---
        Logger logger = logManager.getLogger("com.example.Main");
        logger.setMinLogLevel(LogLevel.INFO); // Minimum level for this logger
        logger.setDestinations(List.of(new ConsoleDestination()));
        logger.info("Application starting up.");
        logger.debug("This is a debug message, it should NOT appear."); // Below INFO
        logger.warn("This is a warning message.");

        // --- 2. A second, independently configured logger ---
        Logger serviceLogger = logManager.getLogger("com.example.UserService");
        serviceLogger.setMinLogLevel(LogLevel.DEBUG); // More verbose for this logger
        serviceLogger.setDestinations(List.of(new ConsoleDestination()));
        serviceLogger.info("User service starting.");
        serviceLogger.debug("Detailed debug output for the user service.");

        // --- 3. Raise the Main logger's verbosity at runtime ---
        logger.setMinLogLevel(LogLevel.DEBUG);
        logger.debug("This debug message should now be visible.");

        try {
            Thread.sleep(500);
            logManager.shutDown();
        } catch (Exception e) {
            System.out.println("Caught exception");
        }
    }
}
