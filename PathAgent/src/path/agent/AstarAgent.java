package path.agent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import path.PathFinderApp;
import path.agent.heuristic.Euclidean;
import path.level.Level;

/**
 * This is the class where it has the descriptions of each line. This is the
 * root of all classes.
 * 
 * @author alwinbhogal
 *
 */
public class AstarAgent extends PathAgent {

	protected PriorityQueue<Node> fringe; // unvisited points
	protected HashSet<Point> visited; // visited points
	protected Node startNode, currentNode, goalNode, successorNode; // have a goal, start, and current node to compare
	// nodes
	protected Euclidean euDistance;
	protected Point point;

	public AstarAgent(Level lvl) {
		super(lvl);

	}

	/**
	 * This is an AStar agent where a priority queue and hash set are made at the
	 * beginning. It is an informed search algorithm that uses a heuristic from the
	 * Heuristic class of the cost of the node and the distance from the goal. It searches the nodes by using the priority queue as a way
	 * to order the nodes. The nodes are then taken out on which one is at the
	 * front. They are then checked and put as a state.
	 */
	@Override
	public List<Point> findPath() {

		euDistance = new Euclidean(goal); // initializes the heuristic

		fringe = new PriorityQueue<Node>(); // asserts the priority queue to order the nodes in the fringe on what fval
											// is closest to the goal
		visited = new HashSet<Point>();
		startNode = new Node(start, root, null); // does not have a parent when first initialized
		goalNode = new Node(goal, null, null); // does not have a parent

		Action[] actions = { Action.N, Action.W, Action.E, Action.S }; // actions pulled from enum but only 4 of them

		currentNode = startNode;

		while (!currentNode.equals(goalNode)) { // if it doesn't equal the goalNode then we search through

			for (int i = 0; i < actions.length; i++) { // a switch statement to go through all of the directions and
														// draws a point

				point = null; // initializes the point as null first
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

				}
				successorNode = new Node(point, currentNode, actions[i]); // makes a successorNode that has a handle on
																			// that point from the actions that were
																			// made

				if (!visited.contains(successorNode.getState()) && this.level.isValid(point)) { // checks that it is not
																								// in the visited
																								// because we do not
																								// want to visit that
																								// again and makes sure
																								// that it is a valid
																								// point and not outside
																								// the boundaries

					successorNode.setFval(euDistance.h(successorNode.getState()) + successorNode.getCost()); // sets the
																												// fValue

					fringe.add(successorNode); // adds the successorNode to the fringe
					visited.add(successorNode.getState()); // adds the point itself to the visited set because it has
															// been visited
					reachedStates.add(successorNode.getState()); // adds it to reached states because this just keeps
																	// track of all of the nodes that are reached
				}

			}

			currentNode = fringe.remove(); // removes the node from the fringe if it has been added and sets it as the
											// current to check. This removes the beginning of the priority queue
											// because the priority queue orders all of the nodes.

		}

		this.path = this.pathFromNode(currentNode); // returns the path from the currentNode to make sure that it is the
													// correct one

		return null; // returns null if there is no path
	}

	/**
	 * This method is a toString method that displays the agent in the GUI
	 */
	public String toString() {
		return "A*Agent";
	}

}
