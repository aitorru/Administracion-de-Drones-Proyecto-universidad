package com.AdministracionDrones;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.RemoteStub;
import java.util.HashMap;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

public class JsonCreator {
    private JSONObject JsonEntry = new JSONObject();
    private static JSONArray ListaDeVuelos = new JSONArray();

    public void guardarJson(int ID, HashMap<String, String> DatosEntrada) {
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
            FileWriter ArchivoRutasEscritor = new FileWriter("rutas.json");
            ArchivoRutasEscritor.write(ListaDeVuelos.toJSONString());
            ArchivoRutasEscritor.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String leerJsonListaDeVuelos(String key){
        return ListaDeVuelos.get(key);
    }
}