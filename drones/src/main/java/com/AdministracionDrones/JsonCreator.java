package com.AdministracionDrones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.python.util.*;

public class JsonCreator {
    private JSONObject JsonEntry = new JSONObject();
    private static JSONArray ListaDeVuelos = new JSONArray();
    private String textoTemp;

    public JsonCreator() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("rutas.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray Lista_Vuelos = (JSONArray) obj;
            ListaDeVuelos = Lista_Vuelos;

            // Iterate over employee array
            // Lista_Vuelos.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void guardarJson(String ID, HashMap<String, String> DatosEntrada) {
        /*
         * HELP Entrada --------------------------------------------------------------
         * HashMap<String, String> capitalCities = new HashMap<String, String>();
         * capitalCities.put("England", "London"); capitalCities.put("Germany",
         * "Berlin"); capitalCities.put("Norway", "Oslo"); capitalCities.put("USA",
         * "Washington DC"); Salida
         * ---------------------------------------------------------------
         * {"USA":"Washington DC","Norway":"Oslo","England":"London","Germany":"Berlin"}
         * En archivo rutas.json
         * 
         * Los datos se mantienen y se escriben los nuevos
         */
        for (String i : DatosEntrada.keySet()) {
            JsonEntry.put(i, DatosEntrada.get(i));
        }
        try {
            JSONObject nuevoVuelo = new JSONObject();
            nuevoVuelo.put(ID, JsonEntry);
            ListaDeVuelos.add(nuevoVuelo);
            System.out.println(ListaDeVuelos.toJSONString());
            FileWriter ArchivoRutasEscritor = new FileWriter("rutas.json");
            ArchivoRutasEscritor.write(ListaDeVuelos.toJSONString());
            ArchivoRutasEscritor.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Object leerJsonID(String key){
        JSONObject objetoTMP = new JSONObject();
        objetoTMP.put("LISTA DE LOS VUELOS", ListaDeVuelos);
        System.out.println(objetoTMP.toJSONString());
        Object vueloID = objetoTMP.get(key);
        return vueloID;
    }
    public Object leerJsonID(Object obj, String key){
        JSONParser jsonParser = new JSONParser();
        JSONArray arrayJson = new JSONArray();
        try {
            arrayJson = (JSONArray) jsonParser.parse(obj.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        arrayJson.forEach(ID -> IterateOverArray((JSONObject) ID, key));
        Object vueloID = textoTemp;
        return vueloID;
        
    }
    public void IterateOverArray(JSONObject vuelo, String key){
        textoTemp = (String) vuelo.get(key);

    }
    public void leerJSON(){
        Properties props = new Properties();
        // props.put("python.home","path to the Lib folder");
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console:
        // Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't
        // respect java accessibility, so that we can access protected members on
        // subclasses
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter pyInterp = new PythonInterpreter();
        pyInterp.exec("print('Hello Python World!')");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("jsonDecoder.py");
        pyInterp.execfile(is);
        pyInterp.close();
    }
}