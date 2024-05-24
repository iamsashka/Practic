package org.example;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static Log instance;

    private Log() {}

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void initializeLogger(Class<?> clazz, String fileName) throws SecurityException, IOException {
        Logger logger = Logger.getLogger(clazz.getName());
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileHandler fileHandler = new FileHandler(fileName, true);
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
        Formatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    public Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}