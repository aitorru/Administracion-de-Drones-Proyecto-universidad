package com.administracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.logger.AdminLogger;

/**
 * Clase de conexión con base de datos de coordenadas en el sistema
 * 
 * @author Aitor Ruiz
 * @version 0.2
 */
public class CoordenadasDB {
	private Connection connGlobal;

	private static final Logger LOGGE = Logger.getLogger(CoordenadasDB.class.getName());
    private Logger LOGGER = new AdminLogger(LOGGE).getLOGGER();

	/**
	 * <h1>Constructor</h1> Constructor que hace la conexión con la base de datos
	 */
	public CoordenadasDB() {
		String url = "jdbc:sqlite::resource:coordenadasDB.sqlite";
		try {
			connGlobal = DriverManager.getConnection(url);
			System.out.println("Connexion establecida");
			if (leerBD() == null) {
				crearTabla();
				LOGGER.warning("Programa corriendo sin coordenadas, la funcionalidad es limitada");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
		}
	}

	/**
	 * <h1>Creador de tabla si no existe</h1> Este metodo privado crea la tabla en
	 * la base de datos si no existe
	 * 
	 */
	private void crearTabla() {
		String sql = "CREATE TABLE IF NOT EXISTS ciudades(\n" + "id INTEGER PRIMARY KEY NOT NULL,\n"
				+ "ciudad text NOT NULL,\n" + "coordenadasX INTEGER, \n" + "coordenadasY INTEGER\n);";
		try {
			PreparedStatement pstmt = connGlobal.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("Tabla de coordenadas creada.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>Lector</h1> lee la base de datos
	 * 
	 * @return {@literal (ArrayList<HashMap<String, String>>)} con un array de mapas de todos los
	 *         datos en la bd, si da excepcion devuelve null
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
			JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
		}
		return listaDeHashMaps;
	}

}