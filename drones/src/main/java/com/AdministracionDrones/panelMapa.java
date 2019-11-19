package com.AdministracionDrones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.InputStream;

/**
 * Clase que carga el mapa en el panel principal
 * 
 * @author Sergio Salgado
 * @version 0.3
 */


public class panelMapa extends JPanel{
	
	private BufferedImage image;

	
	public panelMapa() {              
			InputStream image = this.getClass().getClassLoader().getResourceAsStream("mapa.png");;
	}
	
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);            
	    }
	
	public static void main(String[] args) {
		new panelMapa();
	}
}
