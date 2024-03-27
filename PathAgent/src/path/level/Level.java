package path.level;
import java.awt.Point;
import java.awt.Shape;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * An instance of this class represents a level in a 2D game and provides the environment in 
 * which our agent will search.
 * <p>
 * Our world has a polygon boundary shape and many obstacles.
 * 
 */
public class Level {

	private Shape boundary;
	private List<Shape> obstacles = new LinkedList<>();

	
	/**
	 *  A convenient method to obtain an builder object to help constructor a level. 
	 */
	public static LevelBuilder builder() {
		return new LevelBuilder();
	}
	
	/**
	 * A newly created level at least has a boundary.
	 */
	public Level(Shape bounds) {
		boundary = bounds;
	}


	/**
	 * @return the list of obstacles in our level
	 */
	public List<Shape> obstacles() {
		return this.obstacles;
	}

	
	/**
	 * Tests to see if a specified state/point exists inside our boundary
	 * but not inside any of the obsacles. 
	 * @param p a point to test
	 * @return true if inside the boundary and outside of all obstacles.
	 */
	public boolean isValid(Point p) {

		if (!boundary.contains(p)) return false;
		
		for (Shape s : obstacles) 
			if (s.contains(p)) return false;
				
		return true;
	}

	
	
}
