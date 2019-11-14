package com.RestServer;

import java.util.HashMap;

public class commandExec {
    public String ejecutarComando(String query){
        HashMap<String,String> mapaDeDatos = new HashMap<String,String>();
        char[] caracteres = query.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            String comando = "";
            String contexto = "";
            i = i++;
            boolean cambioDeEntrada = false;
            while(caracteres[i]!='?' || caracteres[i]!='&'){
                if(caracteres[i] == '='){
                    i++;
                    cambioDeEntrada = true;
                } else {
                    if (!cambioDeEntrada){
                        comando = comando + caracteres[i];
                    } else {
                        contexto = contexto + caracteres[i];
                    }
                }
            }
            mapaDeDatos.put(comando, contexto);
        }
        System.out.println("Response: " + mapaDeDatos.toString());
        return mapaDeDatos.toString();
    }
}