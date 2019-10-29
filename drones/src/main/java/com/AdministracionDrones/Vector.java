import static java.lang.Math.sqrt;
package AdministracionDrones;
public class Vector {
	
		public float x;
	    public float y;

	    public Vector(float x, float y) {
	        this.x = x;
	        this.y = y;
	    }

	    public static float length(float x, float y) {
	        return Math.sqrt(x * x + y * y);
	    }

	    public static float distance(Vector first, Vector second) {
	        return length(second.x - first.x, second.y - first.y);
	    }

	    public static Vector direction(float x, float y) {
	        return new Vector(x / Vector.length(x, y), y / length(x, y));
	    
	}
}