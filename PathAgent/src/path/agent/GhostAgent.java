package path.agent;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import path.level.Level;


/**
 * An instance of this class is for demonstration purposes only and represents an agent
 * that does not plan at all and can walk through obstacles on the level.   
 */
public class GhostAgent extends PathAgent {
	

	public GhostAgent(Level lvl) {
		super(lvl);
	}
	
	
	/**
	 * A ghost agent computes the straight path from the start to the goal in 10 equal
	 * steps.
	 */
	public List<Point> findPath() {
		
		
		
		assert start != null;
		assert goal != null;
		
		// this.setEstimator(new ManhattenDistance(goal));  // ignored by this agent
		
		path = new LinkedList<Point>();
		
		double dx = goal.x - start.x; 
		dx = dx / 10;
		
		double dy = goal.y - start.y;
		dy = dy / 10;
		
			
		double x = start.x;
		double y = start.y;
		for (int i = 0; i<10; i++ ) {
			x = x + dx;
			y = y + dy;
			
			path.add(new Point((int)Math.round(x), (int)Math.round(y)));
		}
		
		return this.getPath();
	}
	
	
	/**
	 * We supply a string label to show up on the user interface.
	 */
	public String toString() {
		return "Ghost Agent";
	}


}
