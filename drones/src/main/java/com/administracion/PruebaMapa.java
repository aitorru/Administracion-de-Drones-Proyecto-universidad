package com.administracion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.logging.Logger;
import com.logger.AdminLogger;

public class PruebaMapa {
	
	private static final Logger LOGGE = Logger.getLogger(PruebaMapa.class.getName());
	private Logger LOGGER = new AdminLogger(LOGGE, "pruebamapa.log").getLOGGER();
	
	public PruebaMapa() {
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("mapa.png");
			BufferedImage image = ImageIO.read(is);
			JLabel label = new JLabel(new ImageIcon(image));
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.getContentPane().add(label);
			f.pack();
			f.setLocation(200,200);
			f.setVisible(true);
		} catch (IOException ex) {
			LOGGER.info("Cargando mapa");
		}
	}
}