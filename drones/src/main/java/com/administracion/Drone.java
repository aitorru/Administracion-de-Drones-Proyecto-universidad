package com.administracion;
import java.awt.Graphics2D;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

public class Drone {

	private Vector position;
	private Vector start;
	private Vector target;
	private Vector end;
	private Vector direction;
	private JLabel droneImage;

	private float speed;

	private float distance;

	public Drone(Vector position, Vector target, float speed) {
		this.position = position;
		droneImage = new JLabel();
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "redpoint.png");
		Image img = icon.getImage();
		icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		droneImage.setIcon(icon);
		droneImage.setLocation((int)position.x, (int)position.y);
		droneImage.setSize(10,10);

		
	    // Initialize start as a new Vector with the same x and y coordinates as position
	    this.start = new Vector(position.x, position.y);
		this.target = target;
		this.speed = speed;

	    distance = Vector.distance(start, end);
		direction = Vector.direction(end.x - start.x, end.y - start.y);
		
		
	}

	public void tick() {
	    position.x += direction.x * speed;
		position.y += direction.y * speed;
		

	    if (Vector.distance(start, position) >= distance) {
			
			// The drone has reached the target
			position.x = direction.x ;
			position.y = direction.y ;
			//key
	    }

	   
	}
	public JLabel getImage() {
		return this.droneImage;
	}
}

