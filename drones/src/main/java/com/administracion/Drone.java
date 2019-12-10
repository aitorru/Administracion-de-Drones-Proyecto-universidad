package com.administracion;

public class Drone {

	private Vector position;
	private Vector start;
	private Vector target;
	private Vector end;
	private Vector direction;

	private float speed;

	private float distance;


	public Drone(Vector position, Vector target, float speed) {
	    this.position = position;

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
	    }

	    
	}

}

