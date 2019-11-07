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
			if(leerBD()==null){
				crearTabla();
				System.out.println("Programa corriendo sin coordenadas, la funcionalidad es limitada");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void crearTabla() {
		String sql = "CREATE TABLE IF NOT EXISTS ciudades(\n" + "id INTEGER PRIMARY KEY NOT NULL,\n" + "ciudad text NOT NULL,\n"
				+ "coordenadasX INTEGER, \n" + "coordenadasY INTEGER\n);";
		try {
			PreparedStatement pstmt = connGlobal.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Tabla de coordenadas creada.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HashMap<String, String>> leerBD() {
		String sql = "SELECT id, ciudad, coordenadasX, coordenadasY FROM ciudades";
		ArrayList<HashMap<String, String>> listaDeHashMaps = new ArrayList<HashMap<String, String>>();
		try {
			Statement pstmt = connGlobal.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> mapaTemporal = new HashMap<String, String>();
				mapaTemporal.put("id", Integer.toString(rs.getInt("id")));
				mapaTemporal.put("ciudad", rs.getString("ciudad"));
				mapaTemporal.put("coordenadasX", Integer.toString(rs.getInt("coordenadasX")));
				mapaTemporal.put("coordenadasY", Integer.toString(rs.getInt("coordenadasX")));
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