package com.AdministracionDrones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        objetoTMP.put("LISTA DE VUELOS", ListaDeVuelos);
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
}