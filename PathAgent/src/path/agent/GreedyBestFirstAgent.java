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
 * This class is the general class for the Greedy Best First Search with only
 * having four directions. The description of each line is in AStarAgent.java.
 * 
 * @author alwinbhogal
 *
 */
public class GreedyBestFirstAgent extends PathAgent {

	protected PriorityQueue<Node> fringe; // unvisited points
	protected HashSet<Point> visited; // visited points
	protected Node startNode, currentNode, goalNode, successorNode; // have a goal, start, and current node to compare
	// nodes
	protected Euclidean euDistance;
	protected Point point;

	public GreedyBestFirstAgent(Level lvl) {
		super(lvl);

	}

	/**
	 * This is a GreedyBestFirstSearch agent where a priority queue and hash set are
	 * made at the beginning. It is an informed search algorithm that uses just a
	 * heuristic from the Heuristic class of the cost of the node. It searches the
	 * nodes by using the priority queue as a way to order the nodes. The nodes are
	 * then taken out on which one is at the front. They are then checked and put as
	 * a state.
	 */
	@Override
	public List<Point> findPath() {

		euDistance = new Euclidean(goal);

		fringe = new PriorityQueue<Node>(); // assert the two queues that will be used
		visited = new HashSet<Point>();
		startNode = new Node(start, root, null); // does not have a parent when first initialized
		goalNode = new Node(goal, null, null); // does not have a parent

		Action[] actions = { Action.N, Action.E, Action.W, Action.S };

		currentNode = startNode; // sets the startNode equal to the current at the beginning of the search

		while (!currentNode.equals(goalNode)) {

			for (int i = 0; i < actions.length; i++) {

				point = null;
				// Below are a list of actions of using the enum class
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
				successorNode = new Node(point, currentNode, actions[i]);

				if (!visited.contains(successorNode.getState()) && this.level.isValid(point)) { // has to check it is a
																								// valid point so that
																								// it doesn't hit
																								// boundaries

					successorNode.setFval(euDistance.h(successorNode.getState())); // sets the fValue

					fringe.add(successorNode);
					visited.add(successorNode.getState());
					reachedStates.add(successorNode.getState());
				}

			}

			currentNode = fringe.remove();
		}

		this.path = this.pathFromNode(currentNode); // returns the path from the node, path is drawn in frame
		return null;
	}

	/**
	 * This method is a toString method that displays the agent in the GUI
	 */
	public String toString() {
		return "Greedy Best First Search Agent";
	}

}
