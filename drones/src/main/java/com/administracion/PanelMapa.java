package com.administracion;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.logger.AdminLogger;

public class PanelMapa {
	
	private static final Logger LOGGE = Logger.getLogger(PanelMapa.class.getName());
	private final Logger LOGGER = new AdminLogger(LOGGE, "panelmapa.log").getLOGGER();

	public PanelMapa() {
		try {
			final InputStream is = this.getClass().getClassLoader().getResourceAsStream("mapa.png");
			final BufferedImage image = ImageIO.read(is);
			final JLabel label = new JLabel(new ImageIcon(image));
			final JFrame f = new JFrame();
			//final JPanel locationPanel = new JPanel();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.getContentPane().add(label);
			f.pack();
			f.setLocation(200, 200);
			f.setVisible(true);
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