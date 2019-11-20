package com.administracion;

import javax.swing.SwingUtilities;

import com.rest.RestApplication;

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
                new Login().setVisible(true);
                // GUI Launcher
            }
        });
        new Runnable(){
            @Override
            public void run() {
                new RestApplication().run();
            }
        };
    }
}
