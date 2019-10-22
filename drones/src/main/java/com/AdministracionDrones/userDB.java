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

}