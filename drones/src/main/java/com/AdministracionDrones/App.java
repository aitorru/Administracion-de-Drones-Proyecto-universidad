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
        BackEndAdmin s = new BackEndAdmin();
        HashMap<String, String> capitalCities = new HashMap<String, String>();
        capitalCities.put("idUsuario", "1314");
        capitalCities.put("coordenadasX", "100");
        capitalCities.put("coordenadasY", "100");
        capitalCities.put("horaSalida", "1000");
        capitalCities.put("horaLlegada", "1100");
        capitalCities.put("ciudadSalida", "Madrid");
        capitalCities.put("ciudadLlegada", "Bilbao");
        capitalCities.put("cargaDescripcion", "Baterias");
        HashMap<String, String> ejemplo = new HashMap<String, String>();
        ejemplo.put("idUsuario", "1315");
        ejemplo.put("coordenadasX", "200");
        ejemplo.put("coordenadasY", "100");
        ejemplo.put("horaSalida", "1040");
        ejemplo.put("horaLlegada", "1107");
        ejemplo.put("ciudadSalida", "Madrid");
        ejemplo.put("ciudadLlegada", "Barcelona");
        ejemplo.put("cargaDescripcion", "Baterias");

        // idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlgada, cargaDescripcion
        s.guardarBD(capitalCities);
        s.guardarBD(ejemplo);
        System.out.println(s.leerBD().toString());
        //s.guardarJson("1",capitalCities);
        //System.out.println("1" + s.leerJsonID("LISTA DE VUELOS").toString());
        //System.out.println(s.leerJsonID(s.leerJsonID("LISTA DE VUELOS"), "1"));
        //s.leerJSON();

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
