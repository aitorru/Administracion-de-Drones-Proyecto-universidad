package com.AdministracionDrones;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class JsonCreator {
    private Gson gson = new Gson();
    private JSONObject JsonEntry = new JSONObject();

    public void guardarAJson(HashMap<String, String> DatosEntrada) {
        /*
        * HELP
        Entrada --------------------------------------------------------------
        HashMap<String, String> capitalCities = new HashMap<String, String>();
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        Salida ---------------------------------------------------------------
        {"USA":"Washington DC","Norway":"Oslo","England":"London","Germany":"Berlin"}
        En archivo rutas.json

        Los datos se mantienen y se escriben los nuevos
        */
        for (String i : DatosEntrada.keySet()) {
            JsonEntry.put(i, DatosEntrada.get(i));
        }
        try {
            File f = new File("rutas.json");
            Scanner fileReader = new Scanner(f);
            if (f.exists()){
                String buffer = "";
                while(fileReader.hasNext()){
                    buffer = buffer + " " + fileReader.nextLine(); 
                }
                JSONParser parser = new JSONParser();
                System.out.println(parser.parse());
                FileWriter ArchivoRutasEscritor = new FileWriter("rutas.json");
                ArchivoRutasEscritor.write(JsonEntry.toJSONString());
                ArchivoRutasEscritor.close();
            }
            fileReader.close();
            FileWriter ArchivoRutasEscritor = new FileWriter("rutas.json");
            ArchivoRutasEscritor.write(JsonEntry.toJSONString());
            ArchivoRutasEscritor.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}