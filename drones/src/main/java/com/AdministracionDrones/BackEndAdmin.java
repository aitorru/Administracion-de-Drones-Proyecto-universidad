package com.AdministracionDrones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.python.antlr.PythonParser.return_stmt_return;
import org.python.util.*;

import java.sql.*;

public class BackEndAdmin {
    private String textoTemp;
    private Connection connGlobal;
    private DatabaseMetaData metaGlobal;
    private Statement stmtGlobal;

    public BackEndAdmin() {
        // KIND OF LAZY BUT EFFECTIVE
        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter pyInterp = new PythonInterpreter();
        pyInterp.exec("f = file('dronesDataBase.db', 'w')");
        pyInterp.close();
        try {
            ejecutarBD();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void ejecutarBD() throws SQLException {
        String url = "jdbc:sqlite:dronesDataBase.db";
        connGlobal = DriverManager.getConnection(url);
        if (connGlobal != null) {
            metaGlobal = connGlobal.getMetaData();
            stmtGlobal = connGlobal.createStatement();
            System.out.println("The driver name is " + metaGlobal.getDriverName());
            System.out.println("A new database has been created.");
            String datosEntrada = "CREATE TABLE IF NOT EXISTS dron (\n" + "id INTEGER PRIMARY KEY NOT NULL, \n"
                    + "idUsuario INTEGER NOT NULL, \n " // ID de prpietario del DRON
                    + "coordenadasX INTEGER NOT NULL, \n" + "coordenadasY INTEGER NOT NULL, \n"
                    + "horaSalida INTEGER NOT NULL, \n" + "horaLlegada INTEGER NOT NULL, \n"
                    + "ciudadSalida text NOT NULL, \n" + "ciudadLlegada text NOT NULL, \n"
                    + "cargaDescripcion text\n" + ");";
            stmtGlobal.execute(datosEntrada);
            stmtGlobal.close();
            System.out.println("Created");
        }
    }

    public void guardarBD(HashMap<String, String> DatosEntrada) {
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
        String sql = "INSERT INTO dron(idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlegada, cargaDescripcion) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connGlobal.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(DatosEntrada.get("idUsuario")));
            pstmt.setInt(2, Integer.valueOf(DatosEntrada.get("coordenadasX")));
            pstmt.setInt(3, Integer.valueOf(DatosEntrada.get("coordenadasY")));
            pstmt.setInt(4, Integer.valueOf(DatosEntrada.get("horaSalida")));
            pstmt.setInt(5, Integer.valueOf(DatosEntrada.get("horaLlegada")));
            pstmt.setString(6, DatosEntrada.get("ciudadSalida"));
            pstmt.setString(7, DatosEntrada.get("ciudadLlegada"));
            pstmt.setString(8, DatosEntrada.get("cargaDescripcion"));
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    public ArrayList<HashMap<String,String>> leerBD() {
        String sql = "SELECT id, idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlegada, cargaDescripcion FROM dron";
        ArrayList<HashMap<String,String>> listaDeHashMaps = new ArrayList<HashMap<String,String>>();
        try {
            ResultSet rs = stmtGlobal.executeQuery(sql);
            while(rs.next()){
                HashMap<String, String> mapaTemporal = new HashMap<String, String>();
                mapaTemporal.put("id", Integer.toString(rs.getInt("id")));
                mapaTemporal.put("idUsuario", Integer.toString(rs.getInt("idUsuario")));
                mapaTemporal.put("coordenadasX", Integer.toString(rs.getInt("coordenadasX")));
                mapaTemporal.put("coordenadasY", Integer.toString(rs.getInt("coordenadasY")));
                mapaTemporal.put("horaSalida", Integer.toString(rs.getInt("horaSalida")));
                mapaTemporal.put("ciudadSalida", rs.getString("ciudadSalida"));
                mapaTemporal.put("ciudadLlgada", rs.getString("ciudadLlegada"));
                mapaTemporal.put("cargaDescripcion", rs.getString("cargaDescripcion"));
                listaDeHashMaps.add(mapaTemporal);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (listaDeHashMaps != null) {
            return listaDeHashMaps;
        } else {
            return null;
        }
    }
}