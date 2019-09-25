package proyectoUniversidad;

//import javax.swing.JFrame;

import javax.swing.*;
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
	public static void main( String[] args )
    {
        App aplicacion = new App();
        aplicacion.crearVentana();
    }
    public void crearVentana(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
