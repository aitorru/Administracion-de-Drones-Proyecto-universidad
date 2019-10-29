
public class Drone extends Vector {

	private Vector position;
	private Vector start;
	private Vector target;
	private Vector direction;

	private float speed;

	private float distance;

	...

	public Drone(Vector position, Vector target, float speed, ...) {
	    this.position = position;

	    // Initialize start as a new Vector with the same x and y coordinates as position
	    this.start = new Vector(position.x, position.y);
	    this.target = target;

	    distance = Vector.distance(start, end);
	    direction = Vector.direction(end.x - start.x, end.y - start.y);

	    ...
	}

	public void tick() {
	    position.x += direction.x * speed;
	    position.y += direction.y * speed;

	    if (Vector.distance(start, position) >= distance) {
	        // The ship has reached the target
	    }

	    ...
	}
	

