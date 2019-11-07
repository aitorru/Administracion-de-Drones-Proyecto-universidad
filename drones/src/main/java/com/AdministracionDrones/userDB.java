package com.AdministracionDrones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class userDB {
  private Connection connGlobal;

  public userDB() {
    String url = "jdbc:sqlite::resource:userDB.db";
    try {
      connGlobal = DriverManager.getConnection(url);
      System.out.println("Connexion establecida");
      if(leerBD() == null){
        crearTabla();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void crearTabla() {
    String sql = "CREATE TABLE IF NOT EXISTS usuarios(\n" + "id INTEGER PRIMARY KEY NOT NULL,\n" + "user TEXT, \n"
        + "password TEXT\n);";
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
    String sql = "SELECT id, idUsuario, user, password FROM usuarios";
    ArrayList<HashMap<String, String>> listaDeHashMaps = new ArrayList<HashMap<String, String>>();
    try {
      Statement pstmt = connGlobal.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            while(rs.next()){
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
            return null;
        }
        return listaDeHashMaps;
    }

}
