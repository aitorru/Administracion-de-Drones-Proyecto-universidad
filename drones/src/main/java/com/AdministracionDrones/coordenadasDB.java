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
 * Clase de conexión con base de datos de coordenadas en el sistema
 * 
 * @author Aitor Ruiz
 * @version 0.2
 */
public class coordenadasDB {
	private Connection connGlobal;

	/**
	 * <h1>Constructor</h1> Constructor que hace la conexión con la base de datos
	 */
	public coordenadasDB() {
		String url = "jdbc:sqlite::resource:coordenadasDB.sqlite";
		try {
			connGlobal = DriverManager.getConnection(url);
			System.out.println("Connexion establecida");
			if (leerBD() == null) {
				crearTabla();
				System.out.println("Programa corriendo sin coordenadas, la funcionalidad es limitada");
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
	 * @param null
	 * @return null
	 * @throws null
	 */
	private void crearTabla() {
		String sql = "CREATE TABLE IF NOT EXISTS ciudades(\n" + "id INTEGER PRIMARY KEY NOT NULL,\n"
				+ "ciudad text NOT NULL,\n" + "coordenadasX INTEGER, \n" + "coordenadasY INTEGER\n);";
		try {
			PreparedStatement pstmt = connGlobal.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Tabla de coordenadas creada.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>Lector</h1> lee la base de datos
	 * 
	 * @param null
	 * @return ArrayList<HashMap<String, String>> con un array de mapas de todos los
	 *         datos en la bd
	 * @exception SQLException y hace @{@code return null} si falla
	 * @throws null
	 */
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