package com.rest;

import java.util.ArrayList;
import java.util.HashMap;
import com.administracion.BackEndAdmin;
public class CommandExec {
    /**
     * <h1>ejecutarComando</h1> Ejecuta el comando de entrada.
     * @param query
     * @return
     */
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
        ArrayList<Character> comando, contexto;
        comando = new ArrayList<Character>();
        contexto = new ArrayList<Character>();
        for (int i = 0; i < listaDeChunks.size(); i++) {
            char[] entradaComandos = listaDeChunks.get(i).toCharArray();
            System.out.println(listaDeChunks.get(i));
            boolean cambioDeLinea = false;
            for (int j = 0; j < entradaComandos.length; j++) {
                if(caracteres[j] == '='){
                    cambioDeLinea = true;
                } else {
                    if (cambioDeLinea){
                        contexto.add(entradaComandos[j]);
                    }else{
                        comando.add(entradaComandos[j]);
                    }
                }
            }
            String comandoS = charToString(comando);
            String contextoS = charToString(contexto);
            mapaDeDatos.put(comandoS, contextoS);
            comando.clear();
            contexto.clear();
        }
        BackEndAdmin b = new BackEndAdmin();
        ArrayList<HashMap<String, String>> lecturaDeBase = b.leerBD();
        ArrayList<HashMap<String, String>> salida = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < lecturaDeBase.size(); i++) {
            HashMap<String, String> temp = lecturaDeBase.get(i);
            if(temp.get("id").equals(mapaDeDatos.get("id"))){
                salida.add(temp);
            }
            
        }
        long segundosAcabado = System.currentTimeMillis();
        long resultado = segundosAcabado - segundosInicio;
        return salida.toString() + " Resultado: " + resultado + " Inicio: " + segundosInicio + " Final: " + segundosAcabado;
    }
    /**
     * <h1>Convierte un chararray a </h1>
     * @param entrada de array de char
     * @return String de salida
     */
    public String charToString(ArrayList<Character> entrada){
        String salida = "";
        for (int i = 0; i < entrada.size(); i++) {
            salida = salida + entrada.get(i);
        }
        return salida;
    }
}