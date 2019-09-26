package proyectoUniversidad;

//import javax.swing.JFrame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
/**
 * Creacion de ventana
 *
 *
 */
public class App extends JFrame {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static void main( String[] args ) {
        App aplicacion = new App();
        //aplicacion.crearVentana();
        File sourceFile = new File("directory-source/text1.txt");
		File destinationFile = new File("directory-destination/test1.txt");

        try {
			FileUtils.moveFile(sourceFile, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void crearVentana(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
