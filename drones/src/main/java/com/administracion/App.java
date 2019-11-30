package com.administracion;

import com.logger.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import com.rest.RestApplication;

/**
 * Creacion de ventana
 *
 *
 */
public class App {
    private static final Logger LOGGE = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
		AdminLogger lh = new AdminLogger(LOGGE);
        Logger LOGGER = lh.getLOGGER();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
                LOGGER.info("Iniciada ventana");
                // GUI Launcher
                new PruebaMapa();
            }
        });
        new Runnable(){
            @Override
            public void run() {
                new RestApplication().run();
                LOGGER.info("Iniciado servidor");
            }
        }.run();;
    }
}
