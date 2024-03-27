package path;
import java.awt.Rectangle;

import path.gui.MainFrame;
import path.level.Level;

/**
 * This application allows the user to demonstrate various informed search strategies and their heuristics.
 * We build a level in which a search-based planning (path finding) agent operates, interactively establishes
 * the starting position and goal position and choose a type of agent. We execute and render the search path
 * as well as display metrics about the search.
 * 
 */
public class PathFinderApp {
	
	/**
	 * The distance the agent can travel in the permitted directions.
	 */
	public final static int STRIDE = 10;
	
	/**
	 *  The default horizontal extent of the level.
	 */
	public final static int DEFAULT_FRAME_WIDTH = 1000;
	
	/**
	 *  The default vertical extent of the level.
	 */
	public final static int DEFAULT_FRAME_HEIGHT = 1000;
	


	public static void main(String[] args) {

		/*
		 * First we build up the level (a container of obstacle zones.  Our agent is not
		 * allowed in these zones.
		 */
		Level theLevel = Level.builder().size(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT)
				
				.addZone(new Rectangle(0,0,20,300))
				.addZone(new Rectangle(300,0,20,100))
				.addZone(new Rectangle(310,260,20,40))
				
				.addZone(new Rectangle(0,300,120,100))
				.addZone(new Rectangle(230,300,100,100))
				.addZone(new Rectangle(230,400,200,20))
				.addZone(new Rectangle(230,420,50,280))  
				.addZone(new Rectangle(130,500,100,200))  
				
				
				.addZone(new Rectangle(600,0,400,50))
				.addZone(new Rectangle(600,50,20,50))
				
				.addZone(new Rectangle(400,100,100,200))
				
				.addZone(new Rectangle(600,200,20,100))
				.addZone(new Rectangle(520,380,80,20))   
				
				.addZone(new Rectangle(600,300,200,100))
				.addZone(new Rectangle(800,250,30,200))
				
				.addZone(new Rectangle(300,750,150,50))
				.addZone(new Rectangle(900,450,100,550))
				.addZone(new Rectangle(600,750,200,50))
				.addZone(new Rectangle(800,650,30,150))
				.build();
		
		
		/*
		 * Now display our level and interact with the operator. 
		 */
		MainFrame frame = new MainFrame(theLevel);
		frame.setVisible(true);
	}

}
