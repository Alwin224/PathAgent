package path.agent;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import path.PathFinderApp;
import path.agent.heuristic.Euclidean;
import path.level.Level;

/**
 * This class is similar the AStar agent only using the heuristic is weighted by
 * a scalar of 2. The description of each line is in AStarAgent.java.
 * 
 * @author alwinbhogal
 *
 */
public class WeightedAStarAgent extends PathAgent {

	protected PriorityQueue<Node> fringe;
	protected HashSet<Point> visited;
	protected Node startNode, currentNode, goalNode, successorNode;
	protected Point point;
	protected Euclidean euDistance;
	protected double WEIGHTED_FACTOR = 2.0;

	public WeightedAStarAgent(Level lvl) {
		super(lvl);
	}

	/**
	 * This is an AStar agent where a priority queue and hash set are made at the
	 * beginning. It is an informed search algorithm that uses a heuristic from the
	 * Heuristic class of the cost of the node and the distance from the goal and
	 * scales that heuristic by 2 to make it weighted. It searches the nodes by
	 * using the priority queue as a way to order the nodes. The nodes are then
	 * taken out on which one is at the front. They are then checked and put as a
	 * state.
	 */
	@Override
	public List<Point> findPath() {
		fringe = new PriorityQueue<Node>();
		visited = new HashSet<Point>();
		startNode = new Node(start, null, null);
		goalNode = new Node(goal, null, null);

		euDistance = new Euclidean(goal);

		currentNode = startNode;

		Action[] actions = Action.values();
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

					successorNode.setFval(
							WEIGHTED_FACTOR * (euDistance.h(successorNode.getState()) + successorNode.getCost()));
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

	public String toString() {
		return "Weighted A* Agent";
	}

}
