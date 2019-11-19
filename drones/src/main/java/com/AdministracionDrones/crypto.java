package com.AdministracionDrones;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que se encarga de la criptografia del login del programa
 * 
 * @author Aitor Ruiz
 * @version 0.2
 */

public class crypto {
    /**
     * getSHA es un metodo en el que te devuelve el Strign que le has pasado convertido en SHA  
     * @param input entrada de textp
     * @return byte[] salida de bytes
     * @throws NoSuchAlgorithmException Salida de error si no encuentra el algoritmo
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    /**
     * Convierte un string a hexadecimal
     * @param hash estrada de bytes hash
     * @return String devuelve el texto encriptado
     */
    public static String toHexString(byte[] hash) { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    }
    /**
     * Este metodo hace el trabajo de convertir un string a SHA generando una ventana de error si falla la ejecucion
     * @param strToCrypt texto a encriptar automaticamente
     * @return String devuelve el texto encriptado
     */
    public String StringToCrypto(String strToCrypt){
        try {
            return toHexString(getSHA(strToCrypt));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}