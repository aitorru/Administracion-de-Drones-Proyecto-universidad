# Administracion-de-Drones-Proyecto-universidad ![alt text](https://travis-ci.com/aitorru/Administracion-de-Drones-Proyecto-universidad.svg?token=RrKbwC6VUpYoEbmNPvjQ&branch=master) ![GitHub All Releases](https://img.shields.io/github/downloads/aitorru/Administracion-de-Drones-Proyecto-universidad/total) ![GitHub last commit](https://img.shields.io/github/last-commit/aitorru/Administracion-de-Drones-Proyecto-universidad) ![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/aitorru/Administracion-de-Drones-Proyecto-universidad) :fire:

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

> Aitor Ruiz

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
**id, idUsuario, coordenadasX, coordenadasY, horaSalida, horaLlegada, ciudadSalida, ciudadLlegada, cargaDescripcion**

> Aitor Ruiz

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

> Aitor Ruiz

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

- [X] Modificar de BD

```java
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
```

> Se han creado metodos para hacer la actualizacion de coordenadas rapidamente

- [X] Eliminar Datos de BD

```java
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
```

## Ventana de login

> Jon Ibarreche

- [ ] Investigación

> Aitor Ruiz

- [X] Creacion de metodo de criptografia para acceso a pantalla

```java
public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    }
    public static String toHexString(byte[] hash) {  
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32){  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    }
    public String StringToCrypto(String strToCrypt){
        try {
            return toHexString(getSHA(strToCrypt));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
```

- [X] Ejemplo para lectura de datos rapid

```java
        userDB u = new userDB();
        crypto c = new crypto();
        ArrayList<HashMap<String, String>> listaUsuarios = u.leerBD();
        for(int i = 0; i < listaUsuarios.size();i++){
            HashMap<String, String> temp = listaUsuarios.get(i);
            if (temp.get("user").equals(usuario)){
                if(temp.get("password").equals(c.StringToCrypto(paswd))){
                    temp.get("idUsuario");
                }
            }
        }
```

## Ventana para administrar

> Jon Ibarreche

- [ ] Investigación

## Ventana de mapa

> Sergio Salgado

- [ ] Investigación

## Autores

* **Aitor Ruiz** - *Trabajo inicial, bases de datos, criptografia y coordinacion* - [Aitor Ruiz](https://github.com/aitorru)
* **Sergio Salgado** - *Creacion de mapa 2D* - [Sergio Salgado](https://github.com/ssc1999)
* **Jon Ibarreche** - *Creacion de Login y apartado de usuario* - [Jon ibarreche](https://github.com/JonIbarreche)

## Build // Ejecutar

### Windows

### EXE

Puedes simplemente descargar el [exe](https://github.com/aitorru/Administracion-de-Drones-Proyecto-universidad/releases/download/0.2/Administrador.exe)

### JAR

Tambien puedes descargar el [jar](https://github.com/aitorru/Administracion-de-Drones-Proyecto-universidad/releases/download/0.2/drones-0.2-SNAPSHOT-jar-with-dependencies.jar) directamente y ejecutarlo con

```bash
java -jar drones-*.*-SNAPSHOT-jar-with-dependencies.jar
```

### Maven

Si estas interesado puedes crear el jar desde el proyecto.

```bash
git clone https://github.com/aitorru/Administracion-de-Drones-Proyecto-universidad.git
```

Ahora hay que navegar a Administracion-de-Drones-Proyecto-universidad/drones

```bash
cd Administracion-de-Drones-Proyecto-universidad/drones
```

Y falta con ejecutar lo siguiente

```bash
mvn package
```

Los Jar resultantes estan en Administracion-de-Drones-Proyecto-universidad/drones/target

```bash
cd target
```

## LINUX & MAC

### JAR

Actualmente solo esta disponible en windows de instalador, por lo cual hay que usar el [Jar](https://github.com/aitorru/Administracion-de-Drones-Proyecto-universidad/releases/download/0.2/drones-0.2-SNAPSHOT-jar-with-dependencies.jar)

Ejecutalo con

```bash
java -jar drones-*.*-SNAPSHOT-jar-with-dependencies.jar
```


### Maven

Si estas interesado puedes crear el jar desde el proyecto.

```bash
git clone https://github.com/aitorru/Administracion-de-Drones-Proyecto-universidad.git
```

Ahora hay que navegar a Administracion-de-Drones-Proyecto-universidad/drones

```bash
cd Administracion-de-Drones-Proyecto-universidad/drones
```

Y falta con ejecutar lo siguiente

```bash
mvn package
```

Los Jar resultantes estan en Administracion-de-Drones-Proyecto-universidad/drones/target

```bash
cd target
```


## Creado con // Dependencies

[Jython](https://mvnrepository.com/artifact/org.python/jython)

```xml
<dependencies>
    <dependency>
      <groupId>org.python</groupId>
      <artifactId>jython</artifactId>
      <version>2.7.0</version>
    </dependency>
```

[SQLite](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)

```xml
    <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.28.0</version>
    </dependency>
```

[JSON](https://mvnrepository.com/artifact/org.json/json)

```xml
    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20190722</version>
    </dependency>
```

[JUnit](https://mvnrepository.com/artifact/junit/junit)

```xml
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
</dependencies>
```