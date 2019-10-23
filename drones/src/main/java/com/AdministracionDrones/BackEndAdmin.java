package com.AdministracionDrones;
 
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

import org.python.util.PythonInterpreter;

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
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("creacionDeArchivos.py");
        pyInterp.execfile(is);
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
        return listaDeHashMaps;
    }
    public void UpdateCoordenadasX(int coordenadasX, int id){
        String sql = "UPDATE dron SET coordenadasX = ? WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, coordenadasX);
            psmt.setInt(2, id);
            psmt.executeUpdate();
            psmt.close();
            
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
    public void UpdateCoordenadasY(int coordenadasY, int id){
        String sql = "UPDATE dron SET coordenadasY = ? WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, coordenadasY);
            psmt.setInt(2, id);
            psmt.executeUpdate();
            psmt.close();
            
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
    public void UpdateDatos(int id, int idUsuario, int coordenadasX, int coordenadasY, int horaSalida, int horaLlegada, String ciudadSalida, String ciudadLlegada, String cargaDescripcion){
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
            //TODO: handle exception
        }
    }
    public void eliminarDatos(int id){
        String sql = "DELETE FROM dron WHERE id = ?";
        try {
            PreparedStatement psmt = connGlobal.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.executeQuery();
            psmt.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}