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
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMapa {
	
	private static final Logger LOGGE = Logger.getLogger(PanelMapa.class.getName());
	private final Logger LOGGER = new AdminLogger(LOGGE, "panelmapa.log").getLOGGER();
	private JLabel droneImage;
	private JLayeredPane lpane;
	private JPanel dronPanel;
	

	public PanelMapa(){
		try {
			lpane = new JLayeredPane(); 
			dronPanel = new JPanel();
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("mapa.png");
			BufferedImage image = ImageIO.read(is);
			JLabel label = new JLabel(new ImageIcon(image));
			JFrame f = new JFrame();
			
			JPanel mapaPanel = new JPanel();
			JPanel locationPanel = new JPanel();
			
			Drone dron1 = new Drone(new Vector(25, 25), new Vector(30, 30), 2);
			Drone dron2 = new Drone(new Vector(25, 25), new Vector(25, 25), 3);
			Drone dron3 = new Drone(new Vector(25, 25), new Vector(56, 90), 2);
			Drone dron4 = new Drone(new Vector(25, 25), new Vector(78, 12), 2);
			Drone dron5 = new Drone(new Vector(25, 25), new Vector(156, 41), 2);
		
			f.pack();
			f.setBounds(450, 0, 1057, 761);
			f.setVisible(true);	
			f.add(lpane);
			f.setPreferredSize(new Dimension(1057, 761));
			f.setLayout(new BorderLayout());
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mapaPanel.add(label);
			mapaPanel.setBounds(0, 0, 1057, 761);
			mapaPanel.setVisible(true);

			lpane.setVisible(true);
			lpane.setBounds(0, 0, 1057,761);
			lpane.add(mapaPanel, new Integer(1), 0);
			lpane.add(dronPanel, new Integer(0), 0);			
			

			ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "redpoint.png");
			Image img = icon.getImage();
			icon = new ImageIcon(img.getScaledInstance(10, 10, Image.SCALE_SMOOTH));	
			droneImage = new JLabel(icon);
			droneImage.setIcon(icon);
			droneImage.setBounds(0, 0, 10, 10);
			droneImage.setVisible(true);
			
			dronPanel.add(droneImage);
			dronPanel.setBounds((int) dron1.getPosition().x, (int)dron1.getPosition().y, 10, 10);
			dronPanel.setVisible(true);
			
			new Runnable() {
					@Override
						public void run() {
			
							((Runnable) dronPanel).run();
						}
					}.run();;
	



			
/*			
			f.add(locationPanel, new Integer(0), 0);
			final InputStream targetLocation = this.getClass().getClassLoader().getResourceAsStream("location.png");
			final BufferedImage location = ImageIO.read(targetLocation);
			final JLabel locationJLabel = new JLabel(new ImageIcon(location));
*/		

		} catch (final IOException ex) {
			LOGGER.info("Cargando mapa");
		}
	
	}	
}
