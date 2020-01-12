package com.administracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.python.util.PythonInterpreter;
import org.json.JSONObject;

import com.logger.*;

/**
 * Clase mayor con conexion con la base de datos de los datos de todos los drones del sistema
 * 
 * @author Aitor Ruiz
 * @version 0.2
 */

public class BackEndAdmin {
    private static Connection connGlobal;
    private static DatabaseMetaData metaGlobal;
    private static Statement stmtGlobal;

    private static final Logger LOGGE = Logger.getLogger(BackEndAdmin.class.getName());
    private static final Logger LOGGER = new AdminLogger(LOGGE,"backend.log").getLOGGER();

    /**
	 * <h1>Constructor</h1> Constructor que hace la conexión con la base de datos
     * Crea el archivo en caso de que no exista
     * 
	 */
    public BackEndAdmin() {
        if(!new File("dronesDataBase.sqlite").exists()){
            Properties props = new Properties();
            props.put("python.console.encoding", "UTF-8");
            props.put("python.security.respectJavaAccessibility", "false");
            props.put("python.import.site", "false");
            Properties preprops = System.getProperties();
            PythonInterpreter.initialize(preprops, props, new String[0]);
            PythonInterpreter pyInterp = new PythonInterpreter();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("creacionDeArchivos.py");
            pyInterp.execfile(is);
            LOGGER.info("Creados archivos necesarios en backend.");
            pyInterp.close();
        }
        ejecutarBD();
        String url = "jdbc:sqlite:dronesDataBase.sqlite";
        try {
            connGlobal = DriverManager.getConnection(url);
            metaGlobal = connGlobal.getMetaData();
            stmtGlobal = connGlobal.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
        if (leerBD() == null){
            ejecutarBD();
        }
        LOGGER.info("Conexion establecida con backend.");
    }
    /**
	 * <h1>Lectura de archivo de importacion</h1> Este metodo privado lee un archivo que le utiliza para importar
	 * 
	 * @param NumeroEntrada numero de lectura 
	 * @return {@literal (HashMap<String, String>)}
     * @exception IOException y hace @{@code return null} si falla
	 */
    private HashMap<String, String> leerArchivo(int NumeroEntrada) {
        if (!new File("dronLoader.json").exists()){
            Properties props = new Properties();
            props.put("python.console.encoding", "UTF-8");
            props.put("python.security.respectJavaAccessibility", "false");
            props.put("python.import.site", "false");
            Properties preprops = System.getProperties();
            PythonInterpreter.initialize(preprops, props, new String[0]);
            PythonInterpreter pyInterp = new PythonInterpreter();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("creacionDeEntrada.py");
            pyInterp.execfile(is);
            LOGGER.info("Creados archivo de entrada de datos.");
            pyInterp.close();
        }
        try {
            File f = new File("dronLoader.json");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String resultado = "";
            String st;
            while ((st = br.readLine()) != null) {
                resultado = resultado + st;
            }
            JSONObject obj = new JSONObject(resultado);
            br.close();
            HashMap<String, String> MapaSalida = new HashMap<String, String>();
            try {
                MapaSalida.put("idUsuario", obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("idUsuario"));
                MapaSalida.put("horaSalida",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("horaSalida"));
                MapaSalida.put("horaLlegada",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("horaLlegada"));
                MapaSalida.put("ciudadSalida",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("ciudadSalida"));
                MapaSalida.put("ciudadLlegada",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("ciudadLlegada"));
                MapaSalida.put("cargaDescripcion",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("cargaDescripcion"));
                MapaSalida.put("coordenadasX",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("coordenadasX"));
                MapaSalida.put("coordenadasY",
                        obj.getJSONObject(Integer.toString(NumeroEntrada)).getString("coordenadasY"));
            } catch (Exception e) {
                // Si no existe el numero debe dar error, lo cual no significa que falle.
                LOGGER.warning("Llegado al final del array: " + e.getMessage());
                return null;
            }
            return MapaSalida;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(),"El archvo no existe: " + e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.warning("El archvo no existe: " + e.getMessage());
        }
        return null;
    }
    /**
	 * <h1>Lector</h1> lee el archivo
	 * 
	 * @return {@literal (ArrayList<HashMap<String, String>>)} con un array de mapas de todos los
	 *         datos del archivo
	 */
    public ArrayList<HashMap<String, String>> cargarArchivoParaBaseDeDatos() {
        ArrayList<HashMap<String, String>> ListaParaDB = new ArrayList<HashMap<String, String>>();
        for (int i = 1; leerArchivo(i) != null; i++) {
            ListaParaDB.add(leerArchivo(i));

        }
        return ListaParaDB;
    }

    /**
	 * <h1>Carga de Datos</h1> Carga los archivos en la BD de manera automatica
	 * 
	 * @return {@literal (ArrayList<HashMap<String, String>>)} con un array de mapas de todos los
	 *         datos
	 */
    public ArrayList<HashMap<String, String>> cargarDatosAutomatico() {
        ArrayList<HashMap<String, String>> paraGuardar = cargarArchivoParaBaseDeDatos();
        String coordenadaX = "";
        String coordenadaY = "";
        for (int i = 0; i < paraGuardar.size();) {
            HashMap<String, String> hashMapJson = paraGuardar.get(i);
            CoordenadasDB c = new CoordenadasDB();
            for (int ii = 0; i < c.leerBD().size(); i++) {
                HashMap<String, String> hashMapCoordenadas = c.leerBD().get(ii);
                String ciudad1 = hashMapCoordenadas.get("ciudad");
                String ciudad2 = hashMapJson.get("ciudadSalida");
                // hashMapCoordenadas.get("ciudadSalida").equals(hashMapJson.get("ciudad"))
                if (ciudad1.equals(ciudad2)) {
                    coordenadaX = hashMapCoordenadas.get("coordenadasX");
                    coordenadaY = hashMapCoordenadas.get("coordenadasY");
                    hashMapJson.remove("coordenadasX");
                    hashMapJson.remove("coordenadasY");
                    hashMapJson.put("coordenadasX", coordenadaX);
                    hashMapJson.put("coordenadasY", coordenadaY);
                }
            }
            
            return paraGuardar;
            // TODO cambiar el nombre del objeto para hacerlo adecuado
            // guardarBD(hashMapJson);
        }
        return paraGuardar;
    }
    /**
	 * <h1>Ejecutar BD</h1> Si no ha nada dentro de la BD hay que ejecutarla porque puede ser que no exista.
	 * 
	 */
    public void ejecutarBD() {
        String url = "jdbc:sqlite:dronesDataBase.sqlite";
        try {
            connGlobal = DriverManager.getConnection(url);
            if (connGlobal != null) {
                metaGlobal = connGlobal.getMetaData();
                stmtGlobal = connGlobal.createStatement();
                System.out.println("The driver name is " + metaGlobal.getDriverName());
                System.out.println("A new database has been created.");
                String datosEntrada = "CREATE TABLE IF NOT EXISTS dron (\n" + "id INTEGER PRIMARY KEY NOT NULL, \n"
                        + "idUsuario INTEGER NOT NULL, \n " // ID de prpietario del DRON
                        + "coordenadasX INTEGER, \n" + "coordenadasY INTEGER, \n" + "horaSalida INTEGER NOT NULL, \n"
                        + "horaLlegada INTEGER NOT NULL, \n" + "ciudadSalida text NOT NULL, \n"
                        + "ciudadLlegada text NOT NULL, \n" + "cargaDescripcion text\n" + ");";
                stmtGlobal.execute(datosEntrada);
                stmtGlobal.close();
                LOGGER.info("Creada tabla");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }
    /**
	 * <h1>Guardar BD</h1> Guarda los datos dentro de la BD.
	 * @param DatosEntrada Mapa a guardar 
	 */
    public void guardarBD(HashMap<String, String> DatosEntrada) {
        String sql = "INSERT INTO dron (idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlegada, cargaDescripcion) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pstmt = connGlobal.prepareStatement(sql);
            System.out.println(DatosEntrada.toString());
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
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }
    /**
	 * <h1>Leer BD</h1> Guarda los datos dentro de la BD.
     *  @return {@literal (ArrayList<HashMap<String, String>>)} con un array de mapas de todos los
	 *         datos
	 */
    public ArrayList<HashMap<String, String>> leerBD() {
        String sql = "SELECT id, idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlegada, cargaDescripcion FROM dron";
        ArrayList<HashMap<String, String>> listaDeHashMaps = new ArrayList<HashMap<String, String>>();
        try {
            ResultSet rs = stmtGlobal.executeQuery(sql);
            while (rs.next()) {
                HashMap<String, String> mapaTemporal = new HashMap<String, String>();
                mapaTemporal.put("id", Integer.toString(rs.getInt("id")));
                mapaTemporal.put("idUsuario", Integer.toString(rs.getInt("idUsuario")));
                mapaTemporal.put("coordenadasX", Integer.toString(rs.getInt("coordenadasX")));
                mapaTemporal.put("coordenadasY", Integer.toString(rs.getInt("coordenadasY")));
                mapaTemporal.put("horaSalida", Integer.toString(rs.getInt("horaSalida")));
                mapaTemporal.put("horaLlegada", Integer.toString(rs.getInt("horaLlegada")));
                mapaTemporal.put("ciudadSalida", rs.getString("ciudadSalida"));
                mapaTemporal.put("ciudadLlegada", rs.getString("ciudadLlegada"));
                mapaTemporal.put("cargaDescripcion", rs.getString("cargaDescripcion"));
                listaDeHashMaps.add(mapaTemporal);
                rs.close();
            }
        } catch (SQLException e) {
            // Explicacion: No se puede crear aqui una ventana porque se usa para que falle. Travis no puede hacer una ventana.
            //JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
        return listaDeHashMaps;
    }
    /**
	 * <h1>Modificar BD</h1> Modifica las coordenadas
	 * @param coordenadasX, id Mapa a guardar 
	 */
    public void UpdateCoordenadasX(int coordenadasX, int id) {
        String sql = "UPDATE dron SET coordenadasX = ? WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, coordenadasX);
            psmt.setInt(2, id);
            psmt.executeUpdate();
            psmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }
    /**
	 * <h1>Modificar BD</h1> Modifica las coordenadas
	 * @param coordenadasY, id Mapa a guardar 
	 */
    public void UpdateCoordenadasY(int coordenadasY, int id) {
        String sql = "UPDATE dron SET coordenadasY = ? WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, coordenadasY);
            psmt.setInt(2, id);
            psmt.executeUpdate();
            psmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }
    /**
	 * <h1>Modificar BD</h1> Modifica todos los datos.
     * En desuso por ineficiencia. Si se requiere modificar datos, es mejor eliminar
     * @see #UpdateCoordenadasX(int, int)
     * @see #UpdateCoordenadasY(int, int)
     * @deprecated
	 */
    @Deprecated
    public void UpdateDatos(int id, int idUsuario, int coordenadasX, int coordenadasY, int horaSalida, int horaLlegada,
            String ciudadSalida, String ciudadLlegada, String cargaDescripcion) {
        String sql = "UPDATE dron SET idUsuario = ?, coordenadasX = ?, coordenadasY = ?, horaSalida = ?, horaLlegada = ?, ciudadSalida = ?, ciudadLlegada = ?, cargaDescripcion = ? WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, idUsuario);
            psmt.setInt(2, coordenadasX);
            psmt.setInt(3, coordenadasY);
            psmt.setInt(4, horaSalida);
            psmt.setInt(5, horaLlegada);
            psmt.setString(6, ciudadSalida);
            psmt.setString(7, ciudadLlegada);
            psmt.setString(8, cargaDescripcion);
            psmt.setInt(9, id);
            psmt.executeUpdate();
            psmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }

    /**
	 * <h1>Eliminar un dron de la BD</h1> Eliminar un dron
	 * @param id id del dron 
	 */
    public void eliminarDatos(int id) {
        String sql = "DELETE FROM dron WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.executeQuery();
            psmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }


    /**
	 * <h1>Exporta la BD</h1> Exportar datos
	 * @deprecated
	 */
    @Deprecated
    public void exportarASql(){
        try {
            System.out.println("\u001B31;1mNo hace nada!");
            LOGGER.info("La funcion exportar no tiene funcionalidad");
            //connGlobal.createStatement().executeUpdate("BACKUP DATABASE dron TO DISK = 'dronesDataBase_FallBack.bak'; ");
            connGlobal.createStatement().executeUpdate("backup");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(),"Error por falta de export en sqlite: " + e.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
            LOGGER.warning("Error por falta de export en sqlite: " + e.getMessage());
        }
    }
}