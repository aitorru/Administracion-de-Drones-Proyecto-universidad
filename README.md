# Administracion-de-Drones-Proyecto-universidad ![alt text](https://travis-ci.com/aitorru/Administracion-de-Drones-Proyecto-universidad.svg?token=RrKbwC6VUpYoEbmNPvjQ&branch=master)
Proyecto para administrar una red de drones de reparto a nivel de España.
## Mapa (Parte izquierda)
![Alt text](mapa.PNG?raw=true "Title")
Usando una imagen de OpenStreetMaps vamos a hacer una simulacion de donde estan los drones en este momento.
![Alt text](14466.png?raw=true "Title")

Con un modelo de un dron basico lo moveremos por el mapa simulando el paso del tiempo
## Administracion (Parte derecha)
En la parte derecha de la interfaz hay una serie de desplegables para establecer una ruta

# Roadmap de proyecto

## Manejo de BD

- [x] Creacion de tabla automatica si no existe

```java
public BackEndAdmin() {
        PythonInterpreter pyInterp = new PythonInterpreter();
        pyInterp.exec("f = file('dronesDataBase.db', 'w')");
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
```

Uso de python rapido para crear el archivo si no existe.

Los elemetos que componen la tabla son
> id, idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlegada, cargaDescripcion

- [x] Lectura de BD

```java
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
        if (listaDeHashMaps != null) {
            return listaDeHashMaps;
        } else {
            return null;
        }
```

La lectura hace return de un arraylist de todos los HM. ASi siendo facil la lectura.

- [x] Escritura de BD

```java
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
```

- [ ] Modificar de BD
- [ ] Eliminar Datos de BD

## Ventana de login
- [ ] Investigación


## Ventana para administrar
- [ ] Investigación


## Ventana de mapa
- [ ] Investigación

