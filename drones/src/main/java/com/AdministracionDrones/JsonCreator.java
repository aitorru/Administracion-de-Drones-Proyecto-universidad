package com.AdministracionDrones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonCreator {
    private JSONObject JsonEntry = new JSONObject();
    private static JSONArray ListaDeVuelos = new JSONArray();

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

    private static void parseEmployeeObject(JSONObject employee) {
        // Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        // Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        // Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        // Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
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

    public String leerJsonID(String key){
        JSONObject objetoTMP = new JSONObject();
        objetoTMP.put("LISTA DE VUELOS", ListaDeVuelos);
        String vueloID = (String) objetoTMP.get(key);
        return vueloID;
    }
    public String leerJsonID(int key){
        JSONObject objetoTMP = new JSONObject();
        objetoTMP.put("LISTA DE VUELOS", ListaDeVuelos);
        String vueloID = (String) objetoTMP.get(key);
        return vueloID;
    }
}