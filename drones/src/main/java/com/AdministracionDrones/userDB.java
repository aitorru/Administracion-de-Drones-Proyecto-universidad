package com.AdministracionDrones;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class userDB{
    private Connection connGlobal;
    public userDB(){
        String url = "jdbc:sqlite::resource:userDB.db";
        try {
            connGlobal = DriverManager.getConnection(url);
            System.out.println("Connexion establecida");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void crearTabla(){
      String sql = "CREATE TABLE IF NOT EXISTS usuarios(\n" +
      "id INTEGER PRIMARY KEY NOT NULL" +
      "user TEXT, \n" +
      "password TEXT\n);";
      try {
        PreparedStatement pstmt = connGlobal.prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();

      } catch(Exception e) {

      }
    }

}
