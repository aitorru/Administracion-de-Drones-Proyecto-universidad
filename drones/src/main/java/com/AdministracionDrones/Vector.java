package com.AdministracionDrones;

import static java.lang.Math.sqrt;
public class Vector {
	
		public float x;
	    public float y;

	    public Vector(float x, float y) {
	        this.x = x;
	        this.y = y;
	    }

	    public static float length(float x, float y) {
	        return (float) Math.sqrt(x * x + y * y);
	    }

	    public static float distance(Vector first, Vector second) {
	        return length(second.x - first.x, second.y - first.y);
	    }

	    public static Vector direction(float x, float y) {
	        return new Vector(x / Vector.length(x, y), y / length(x, y));
	    
	}
}