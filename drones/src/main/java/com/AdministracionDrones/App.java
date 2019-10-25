package com.AdministracionDrones;

import java.util.HashMap;

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
        //s.guardarBD(capitalCities);
        //s.guardarBD(ejemplo);
        //System.out.println(s.leerBD().toString());
        crypto c = new crypto();
        //System.out.println(c.StringToCrypto("root"));
        userDB u = new userDB();
        System.out.println(u.leerBD().toString());

    }
}
