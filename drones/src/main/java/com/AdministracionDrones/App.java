package com.AdministracionDrones;

import javax.swing.SwingUtilities;
import com.RestServer.*;

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
        new Runnable(){
            @Override
            public void run() {
                RestApplication server = new RestApplication();
                server.run();
                System.out.println("Servidor iniciado");
            }
        }.run();;
    }
}
