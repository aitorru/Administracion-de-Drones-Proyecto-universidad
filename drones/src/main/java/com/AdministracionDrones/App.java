package com.AdministracionDrones;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.util.HashMap;

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
        /**
         * SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App a = new App();
                a.crearVentana();
            }
        });
         */
        JsonCreator s = new JsonCreator();
        HashMap<String, String> capitalCities = new HashMap<String, String>();
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        //s.guardarJson("2",capitalCities);
        System.out.println(s.leerJsonID("LISTA DE VUELOS").toString());
        System.out.println(s.leerJsonID(s.leerJsonID("LISTA DE VUELOS"), "0").toString());

    }
    public App(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 
        100));
        this.setSize(400,400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    public void crearVentana(){
        PanelMapa = new JPanel();
        PanelMapa.setPreferredSize(new Dimension(30, 100));
        PanelUsuario = new JPanel();
        PanelMapa.setBackground(new Color(1));

        this.getContentPane().add(PanelMapa);
        this.getContentPane().add(PanelUsuario);
        this.pack();
    }
}
