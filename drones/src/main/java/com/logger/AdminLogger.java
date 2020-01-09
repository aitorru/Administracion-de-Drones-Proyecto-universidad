package com.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AdminLogger {
    private  Logger LOGGER;
    private FileHandler fileH;
    //private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    public AdminLogger(Logger log, String path){
        LOGGER = log;
        try {
            File dir = new File("logs");
            if (!dir.isDirectory()){
                dir.mkdirs();
            }
            fileH = new FileHandler("logs" + File.separator +  path);
            fileH.setFormatter(new SimpleFormatter());
            fileH.setLevel(Level.ALL);
            LOGGER.addHandler(fileH);
        } catch (SecurityException | IOException e) {
        }
    }
    public Logger getLOGGER(){
        return LOGGER;
    }
    
}