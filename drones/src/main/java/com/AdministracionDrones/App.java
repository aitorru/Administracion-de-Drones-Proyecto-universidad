package com.AdministracionDrones;

import javax.swing.*;


/**
 * Creacion de ventana
 *
 *
 */
public class App extends JFrame{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App a = new App();
                a.crearVentana();
            }
        });
    }
    public void crearVentana(){
        this.setSize(400,400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
