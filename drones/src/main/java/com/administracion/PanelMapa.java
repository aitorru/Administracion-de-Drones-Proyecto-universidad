package com.administracion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que crea un panel con el mapa
 * 
 * @author Sergio Salgado
 * @version 0.3
 */

public class PanelMapa extends JPanel{
	
	private BufferedImage image;
	
	/**
     * panelMapa() es una clase que devuelve un panel con la imagen que tiene dentro
     */
	
	public PanelMapa() {              
			InputStream image = this.getClass().getClassLoader().getResourceAsStream("mapa.png");;
	}
	
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);            
	    }
		
	/**
	 ** La clase principal me crea un nuevo panelMapa()
	 */
	 
	public static void main(String[] args) {
		new PanelMapa().setVisible(true);
		new PruebaMapa();
	}
}
