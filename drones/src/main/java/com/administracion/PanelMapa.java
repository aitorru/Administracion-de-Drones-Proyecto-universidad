package com.administracion;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.logger.AdminLogger;
import java.awt.BorderLayout;

public class PanelMapa {
	
	private static final Logger LOGGE = Logger.getLogger(PanelMapa.class.getName());
	private final Logger LOGGER = new AdminLogger(LOGGE, "panelmapa.log").getLOGGER();
	private JLabel droneImage;
	
	public PanelMapa() {
		try {
			JLayeredPane lpane = new JLayeredPane();
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("mapa.png");
			BufferedImage image = ImageIO.read(is);
			JLabel label = new JLabel(new ImageIcon(image));
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.getContentPane().add(label);
			f.pack();
			f.setLocation(200, 200);
			f.setVisible(true);
			JPanel dronPanel = new JPanel();
			JPanel mapaPanel = new JPanel();
			JPanel locationPanel = new JPanel();
			

			Drone dron1 = new Drone(new Vector(25, 25), new Vector(30, 30), 2);
			
			droneImage = new JLabel();
			ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "redpoint.png");
			Image img = icon.getImage();
			icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));	
/*			
			f.add(locationPanel, new Integer(0), 0);
			final InputStream targetLocation = this.getClass().getClassLoader().getResourceAsStream("location.png");
			final BufferedImage location = ImageIO.read(targetLocation);
			final JLabel locationJLabel = new JLabel(new ImageIcon(location));
*/		
			droneImage.setIcon(icon);
			droneImage.setLocation((int) dron1.getPosition().x, (int)dron1.getPosition().y);
			droneImage.setSize(10,10);
			droneImage.setVisible(true);
			
			f.add(lpane, BorderLayout.CENTER);
			dronPanel.add(droneImage);	
			lpane.add(mapaPanel, new Integer(0), 0);
			lpane.add(dronPanel, new Integer(1), 0);
			f.getContentPane().add(mapaPanel);

		} catch (final IOException ex) {
			LOGGER.info("Cargando mapa");
		}
	}
}