package com.AdministracionDrones;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Creacion de ventana
 *
 *
 */
public class App extends JFrame{
    JPanel PanelMapa;
    JPanel PanelUsuario;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //login a = new login();
            }
        });
        BackEndAdmin b = new BackEndAdmin();
        crypto c = new crypto();
        System.out.println(c.StringToCrypto("root"));
        userDB u = new userDB();
        System.out.println(u.leerBD().toString());

    }
}
