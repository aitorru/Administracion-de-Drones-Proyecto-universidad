package com.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AdminLogger {
    private  Logger LOGGER;
    private FileHandler fileH;
    //private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public AdminLogger(Logger log){
        LOGGER = log;
        try {
            fileH = new FileHandler("administracion.log");
            fileH.setFormatter(new SimpleFormatter());
            fileH.setLevel(Level.ALL);
            LOGGER.addHandler(fileH);
        } catch (SecurityException | IOException e) {
        }
    }
    public AdminLogger(Logger log, String path){
        LOGGER = log;
        try {
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