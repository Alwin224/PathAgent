package path.level;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import path.PathFinderApp;

/**
 * Use an instance of this class to construct a level in the fluent style.  The 
 * instance variables help us define the parameters we use to build.   
 */
public class LevelBuilder {
	
	private final static int FRAME_PADDING = 20;
	
	private int width = PathFinderApp.DEFAULT_FRAME_WIDTH-FRAME_PADDING;
	
	private int height = PathFinderApp.DEFAULT_FRAME_HEIGHT-FRAME_PADDING;
	
	private List<Shape> zones = new LinkedList<>();
		
	/*
	 * Default constructor
	 */
	public LevelBuilder() {
	}
	
	/**
	 *  Create and configures a new level.
	 */
	public Level build() {
		
		Level lvl = new Level(new Rectangle(width,height));
		lvl.obstacles().clear();
		lvl.obstacles().addAll(zones);
		return lvl;
	}
	
	/**
	 * Method do set the rectangular boundary of the level.  Needed
	 * to build a level.
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public LevelBuilder size(int width, int height) {
		this.width = width;
		this.height = height;
		
		return this;
		
	}
	
	/**
	 * Adds a obstacle zone/shape to the world 
	 * 
	 * @param s
	 * @return
	 */
	public LevelBuilder addZone(Shape s) {
		this.zones.add(s);
		return this;
	}
	
	
	

}
