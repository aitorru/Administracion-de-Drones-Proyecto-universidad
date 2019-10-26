package com.AdministracionDronesTester;

import org.junit.*;

import static org.junit.Assert.assertTrue;

import java.io.File;

import com.AdministracionDrones.coordenadasDB;

public class tester {
    @Test
    public void assertAitorruPart(){
        coordenadasDB c = new coordenadasDB();
        c.leerBD();
        System.out.println(c.leerBD().toString());
        //BackEndAdmin b = new BackEndAdmin();
        File archivoCreado = new File("dronesDataBase.db");
        assertTrue(archivoCreado.exists());
    }

}