package com.AdministracionDronesTester;

import org.junit.*;

import static org.junit.Assert.assertTrue;

import java.io.File;

//import com.AdministracionDrones.*;

public class tester {
    @Test
    public void assertCrypto(){
        //BackEndAdmin b = new BackEndAdmin();
        File archivoCreado = new File("dronesDataBase.db");
        assertTrue(archivoCreado.exists());
    }

}