package com.administracion;

import java.io.File;
import com.rest.RestApplication;

/**
 * Creacion de ventana
 *
 *
 */
public class App {
    public static void main(final String[] args) throws InterruptedException {
        Runnable cleaner = new Runnable() {
            @Override
            public void run() {
                final File logs = new File("logs");
                final File[] listOfLogs = logs.listFiles();
                for (int i = 0; i < listOfLogs.length; i++) {
                    listOfLogs[i].delete();
                }
            }
        };
        Runnable server = new Runnable() {
            @Override
            public void run() {
                new RestApplication().run();
            }
        };
        Runnable GUI = new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
                // GUI Launcher
            }
        };

        Thread cleanerThread = new Thread(cleaner);
        Thread guiThread = new Thread(GUI);
        Thread serverThread = new Thread(server);
        cleanerThread.start();
        while (cleanerThread.isAlive()) {
            guiThread.interrupt();
            serverThread.interrupt();
        }
        guiThread.start();
        serverThread.start();
    }
}
