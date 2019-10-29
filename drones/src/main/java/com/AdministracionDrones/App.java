package com.AdministracionDrones;

import javax.swing.SwingUtilities;

/**
 * Creacion de ventana
 *
 *
 */
public class App{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login().setVisible(true);
                // GUI Launcher
            }
        });
    }
}
