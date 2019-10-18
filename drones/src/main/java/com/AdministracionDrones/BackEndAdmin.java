package com.AdministracionDrones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import org.python.util.*;

import java.sql.*;

public class BackEndAdmin {
    private String textoTemp;
    private Connection connGlobal;
    private DatabaseMetaData metaGlobal;
    private Statement stmtGlobal;

    public BackEndAdmin() {
        //KIND OF LAZY BUT EFFECTIVE
        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter pyInterp = new PythonInterpreter();
        pyInterp.exec("f = file('dronesDataBase.db', 'w')");
        pyInterp.close();
        ejecutarBD();
    }

    public void ejecutarBD(){
        String url = "jdbc:sqlite:dronesDataBase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                connGlobal = conn;
                DatabaseMetaData meta = connGlobal.getMetaData();
                metaGlobal = meta;
                Statement stmt = connGlobal.createStatement();
                stmtGlobal = stmt;
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                String datosEntrada = "CREATE TABLE IF NOT EXISTS drones (\n"
                + "idUsuario INTEGER PRIMARY KEY NOT NULL, \n " //ID de prpietario del DRON
                + "coordenadasX INTEGER NOT NULL, \n"
                + "coordenadasY INTEGER NOT NULL, \n"
                + "horaSalida INTEGER NOT NULL, \n"
                + "horaLlegada INTEGER NOT NULL, \n"
                + "ciudadSalida text NOT NULL, \n"
                + "ciudadLlgada text NOT NULL, \n"
                + "cargaDescripcion text\n"
                + ");";
                stmtGlobal.execute(datosEntrada);
                System.out.println("tabla creada");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarBD(String ID, HashMap<String, String> DatosEntrada) {
        /*
         * HELP Entrada --------------------------------------------------------------
         * HashMap<String, String> capitalCities = new HashMap<String, String>();
         * capitalCities.put("England", "London"); capitalCities.put("Germany",
         * "Berlin"); capitalCities.put("Norway", "Oslo"); capitalCities.put("USA",
         * "Washington DC"); Salida
         * ---------------------------------------------------------------
         * {"USA":"Washington DC","Norway":"Oslo","England":"London","Germany":"Berlin"}
         * En tabla de base de datos
         * 
         * Los datos se mantienen y se escriben los nuevos
         */

    }
}