package com.AdministracionDronesTester;

import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import com.AdministracionDrones.*;

public class tester {
    @Test
    public void assertDB(){
        String expected;
        System.out.println("Empezando test");
        coordenadasDB c = new coordenadasDB();
        System.out.println(c.leerBD().toString());
        expected = "[]";
        assertEquals(expected, c.leerBD().toString());
        userDB d = new userDB();
        System.out.println(d.leerBD().toString());
        expected = "[{password=4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2, idUsuario=1111, id=1, user=root}]";
        assertEquals(expected, d.leerBD().toString());
        BackEndAdmin b = new BackEndAdmin();
        assertTrue(b.ejecutarBD());
        System.out.println("Terminando test");
    }
    @Test
    public void assertCrypto(){
        String expected;
        System.out.println("Empezando test");
        crypto c = new crypto();
        System.out.println(c.StringToCrypto("root"));
        expected = "4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2";
        assertEquals(expected, c.StringToCrypto("root"));
        System.out.println("Terminando test");

    }

    @Test
    public void assertFile(){
        File f = new File("..\\dronesDataBase.db");
        if (f.exists()){
            assertTrue(f.exists());
        } else {
            File ultimoRecurso = new File("dronesDataBase.db");
            assertTrue(ultimoRecurso.exists());
        }
    }

}