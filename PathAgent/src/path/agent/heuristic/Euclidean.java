package path.agent.heuristic;

import java.awt.Point;

public class Euclidean extends Heuristic {

	public Euclidean(Point goal) {
		super(goal);
	}

	/**
	 * This method returns the euclidean distance. The way to find it is by taking
	 * the change in x from the goal to the currentNode in hand and the change in y
	 * from the goal to the currentNode in hand. You then multiply the changes by
	 * each other and add them. Then take the square root and return that.
	 * 
	 * @param nodeState
	 * @return euclideanLine
	 */
	@Override
	public double h(Point nodeState) {
		double deltaX = goalState.getX() - nodeState.getX(); // change in X
		double deltaY = goalState.getY() - nodeState.getY(); // change in Y

		double euclideanLine = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

		return euclideanLine;
	}

}
