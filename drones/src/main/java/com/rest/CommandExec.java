package com.rest;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandExec {
    public String ejecutarComando(String query){
        System.out.println();
        long segundosInicio = System.currentTimeMillis();
        HashMap<String,String> mapaDeDatos = new HashMap<String,String>();
        ArrayList<String> listaDeChunks = new ArrayList<String>();
        String entrada = "";
        char[] caracteres = query.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            if(caracteres[i] == '&'){
                listaDeChunks.add(entrada);
                entrada = "";
            } else {
                entrada = entrada + caracteres[i];
            }
        }
        listaDeChunks.add(entrada);
        long segundosAcabado = System.currentTimeMillis();
        long resultado = segundosAcabado - segundosInicio;
        return listaDeChunks.toString() + " Resultado: " + resultado + " Inicio: " + segundosInicio + " Final: " + segundosAcabado;
    }
}