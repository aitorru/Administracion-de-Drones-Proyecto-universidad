package com.AdministracionDrones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class coordenadasDB {
    private Connection connGlobal;

    public coordenadasDB() {
        String url = "jdbc:sqlite::resource:coordenadasDB.db";
        try {
            connGlobal = DriverManager.getConnection(url);
            System.out.println("Connexion establecida");
            crearTabla();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS ciudades(\n" + "id INTEGER PRIMARY KEY NOT NULL,\n" + "coordenadasX INTEGER, \n"
                + "coordenadasY INTEGER\n);";
        try {
            PreparedStatement pstmt = connGlobal.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Tabla de usuario creada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> leerBD() {
        String sql = "SELECT id, coordenadasX, coordenadasY FROM ciudades";
        ArrayList<HashMap<String, String>> listaDeHashMaps = new ArrayList<HashMap<String, String>>();
        try {
            Statement pstmt = connGlobal.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                HashMap<String, String> mapaTemporal = new HashMap<String, String>();
                mapaTemporal.put("id", Integer.toString(rs.getInt("id")));
                mapaTemporal.put("idUsuario", Integer.toString(rs.getInt("idUsuario")));
                mapaTemporal.put("user", rs.getString("user"));
                mapaTemporal.put("password", rs.getString("password"));
                listaDeHashMaps.add(mapaTemporal);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaDeHashMaps;
    }

}