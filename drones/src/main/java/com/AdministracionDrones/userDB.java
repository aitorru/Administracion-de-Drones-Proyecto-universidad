package com.AdministracionDrones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase para recoger las credenciales de la bd
 * 
 * @author Aitor Ruiz
 * @version 0.2
 */
public class userDB {
  private Connection connGlobal;

  /**
   * <h1>Constructor</h1> Constructor que hace la conexión con la base de datos
   */
  public userDB() {
    String url = "jdbc:sqlite::resource:userDB.sqlite";
    try {
      connGlobal = DriverManager.getConnection(url);
      System.out.println("Connexion establecida");
      if (leerBD() == null) {
        crearTabla();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * <h1>Creador de tabla si no existe</h1> Este metodo privado crea la tabla en
   * la base de datos si no existe
   * 
   */
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

  /**
   * <h1>Lector</h1> lee la base de datos
   * 
   * @return {@literal (ArrayList<HashMap<String, String>>)} con un array de mapas de todos los
   *         datos en la bd y devuelve null si falla
   */
  public ArrayList<HashMap<String, String>> leerBD() {
    String sql = "SELECT id, idUsuario, user, password FROM usuarios";
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
      return null;
    }
    return listaDeHashMaps;
  }

}
