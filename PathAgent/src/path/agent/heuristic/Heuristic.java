package path.agent.heuristic;
import java.awt.Point;

/**
 * Concrete subclass of this class calculates the h value used in the A* search in a specific manner.  
 */
public abstract class Heuristic {
	
	protected Point goalState;
	
	
	public Heuristic() {
		this.goalState = null;
	}
	
	
	/**
	 * Primary constructor ensures that we call the initialization algorithm which 
	 * can be overridden if necessary.
	 * @param goal
	 */
	public Heuristic(Point goal) {
		this.initialize(goal);
	}

	
	
	/**
	 * Override this as needed. 
	 * @param goal
	 */
	public void initialize(Point goal) {
		this.goalState = goal;
	}
	

	/**
	 * Answers the distance from the current node state to the goal state.  Concrete subclasses 
	 * can override 
	 * @param nodeState
	 * @return
	 */
	public abstract double h(Point nodeState);
	
}
