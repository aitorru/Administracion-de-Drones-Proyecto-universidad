package com.tester;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import com.administracion.*;

public class tester {

    @Before
    public void antes(){
        System.out.println("Empezando test ------------------------------------------");
    }
    @After
    public void despues(){
        System.out.println("Terminando test -----------------------------------------");
    }

    @Test
    public void assertDB(){
        String expected;
        CoordenadasDB c = new CoordenadasDB();
        System.out.println(c.leerBD().toString());
        expected = "[{coordenadasY=100, ciudad=Bilbao, coordenadasX=100, id=1}, {coordenadasY=200, ciudad=Madrid, coordenadasX=200, id=2}]";
        assertEquals(expected, c.leerBD().toString());
        UserDB d = new UserDB();
        System.out.println(d.leerBD().toString());
        expected = "[{password=4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2, idUsuario=1111, id=1, user=root}]";
        assertEquals(expected, d.leerBD().toString());
        BackEndAdmin b = new BackEndAdmin();
        //assertTrue(b.ejecutarBD());
    }
    @Test
    public void assertCrypto(){
        String expected;
        Crypto c = new Crypto();
        System.out.println(c.StringToCrypto("root"));
        expected = "4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2";
        assertEquals(expected, c.StringToCrypto("root"));

    }

    @Test
    public void assertFile(){
        File f = new File("..\\dronesDataBase.sqlite");
        if (f.exists()){
            assertTrue(f.exists());
            System.out.println("Existe");
        } else {
            File ultimoRecurso = new File("dronesDataBase.sqlite");
            System.out.println("Existe");
            assertTrue(ultimoRecurso.exists());
        }

    }

}
