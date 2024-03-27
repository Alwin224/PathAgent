package path.agent;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * An instance of this class can serve as a tree node in the agents search.  Since we 
 * are dealing with a planning agent, the state is encoded as a navigation point.  An position
 * in the level.
 * <p>
 * Every search tree node is born with an empty list of children which can be expanded as the 
 * tree node is tested/expanded during the search.
 */
public class Node implements Comparable<Node> {
	
	
	private Point state;	// current point
	
	private double cost = 0.0;     // path cost so far
	
	private double fval = 0.0;	// estimate journey cost through this node
	
	private int depth;  	// depth of node in search tree
	
	private Action actionFromParent;   // what action did we take to get here (from parent)
	
	private Node parent;	// handle on parent node from 
	
	private List<Node> children = new LinkedList<Node>();
	

	
	/**
	 * Primary constructor.   Create a node with state by applying the action from the
	 * parent.   
	 * 
	 * @param p
	 * @param par
	 * @param act
	 */
	public Node(Point p, Node par, Action act) {
		this.state = p;
		this.parent = par;
		
		if (this.parent != null) {
			this.cost = parent.cost + this.state.distance(parent.state);
			this.depth = parent.depth + 1;
			this.actionFromParent = act;
		}

	}

	

	/**
	 * A node must be a comparable to have a natural ordering in the priority queue
	 * based fringe.  The search tree nodes are ordered by fval.  We add a small fudge
	 * factor so that if two node are essential the same (within +/- epsilon) the starts
	 * are considered "equal".
	 * <p>
	 * Having this method enables our nodes to participate in java's ordering collections.
	 */
	@Override
	public int compareTo(Node o) {
		
		final double epsilon = 0.01;
		
		if (this.fval < (o.fval-epsilon)) return -1;
		if (this.fval > (o.fval+epsilon)) return 1;
		return 0;
	}
	
	
	
	
	/*  --------------------------- GETTERS AND SETTERS --------------------------------- */
	
	public Point getState() {
		return state;
	}

	public void setState(Point state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double d) {
		this.cost = d;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Action getActionFromParent() {
		return actionFromParent;
	}

	public void setActionFromParent(Action actionFromParent) {
		this.actionFromParent = actionFromParent;
	}


	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}



	/**
	 * Hash code is needed so we can use Java's hash based collections (HashSet and HashMap).
	 */
	@Override
	public int hashCode() {
		return Objects.hash(state);
	}



	/**
	 * However we consider two nodes are the same if they are based on the same state (point in
	 * the level).  Thus, we define equality to be only based on the node's state (point).   When
	 * we are looking to see if a point exists in the fringe,  this method is the basis.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(state, other.state);
	}



	public double getFval() {
		return fval;
	}



	public void setFval(double fval) {
		this.fval = fval;
	}



	


}
