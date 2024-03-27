package path.agent;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import path.PathFinderApp;
import path.agent.heuristic.Euclidean;
import path.level.Level;

/**
 * This is an optimized version of the AStarAgent class with adding 4 other
 * directions other than the first four. The description of each line is in
 * AStarAgent.java.
 * 
 * @author alwinbhogal
 *
 */
public class AStarAgentDirectionsAdded extends PathAgent {
	protected PriorityQueue<Node> fringe; // unvisited points
	protected HashSet<Point> visited; // visited points
	protected Node startNode, currentNode, goalNode, successorNode; // have a goal, start, and current node to compare
	// nodes
	protected Euclidean euDistance;
	protected Point point;

	public AStarAgentDirectionsAdded(Level lvl) {
		super(lvl);

	}

	/**
	 * This is an AStar agent where a priority queue and hash set are made at the
	 * beginning. It is an informed search algorithm that uses a heuristic from the
	 * Heuristic class of the cost of the node and the distance from the goal. It
	 * searches the nodes by using the priority queue as a way to order the nodes.
	 * The nodes are then taken out on which one is at the front. They are then
	 * checked and put as a state. The difference between this and the AStar agent
	 * is that this includes 4 other directions so it is optimized
	 */
	@Override
	public List<Point> findPath() {

		euDistance = new Euclidean(goal);

		fringe = new PriorityQueue<Node>();
		visited = new HashSet<Point>();
		startNode = new Node(start, root, null);
		goalNode = new Node(goal, null, null);

		Action[] actions = Action.values();

		currentNode = startNode;

		while (!currentNode.equals(goalNode)) {

			for (int i = 0; i < actions.length; i++) {

				point = null;
				switch (actions[i]) {
				case N:
					point = new Point(currentNode.getState().x, currentNode.getState().y + PathFinderApp.STRIDE);
					break;
				case S:
					point = new Point(currentNode.getState().x, currentNode.getState().y - PathFinderApp.STRIDE);
					break;
				case E:
					point = new Point(currentNode.getState().x + PathFinderApp.STRIDE, currentNode.getState().y);
					break;
				case W:
					point = new Point(currentNode.getState().x - PathFinderApp.STRIDE, currentNode.getState().y);
					break;
				case NE:
					point = new Point(currentNode.getState().x + PathFinderApp.STRIDE,
							currentNode.getState().y + PathFinderApp.STRIDE);
					break;
				case NW:
					point = new Point(currentNode.getState().x - PathFinderApp.STRIDE,
							currentNode.getState().y + PathFinderApp.STRIDE);
					break;
				case SE:
					point = new Point(currentNode.getState().x + PathFinderApp.STRIDE,
							currentNode.getState().y - PathFinderApp.STRIDE);
					break;
				case SW:
					point = new Point(currentNode.getState().x - PathFinderApp.STRIDE,
							currentNode.getState().y - PathFinderApp.STRIDE);
					break;
				}
				successorNode = new Node(point, currentNode, actions[i]);

				if (!visited.contains(successorNode.getState()) && this.level.isValid(point)) {
					successorNode.setFval(euDistance.h(successorNode.getState()) + successorNode.getCost());

					fringe.add(successorNode);
					visited.add(successorNode.getState());
					reachedStates.add(successorNode.getState());
				}

			}

			currentNode = fringe.remove();
		}

		this.path = this.pathFromNode(currentNode);
		return null;
	}

	/**
	 * This method is a toString method that displays the agent in the GUI
	 */
	public String toString() {
		return "A*Agent Optimized";
	}
}
