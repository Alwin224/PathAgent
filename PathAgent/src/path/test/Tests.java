package path.test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void test_HashSet_with_Points() {
		
		HashSet<Point> set = new HashSet<Point>();
		
		set.add(new Point(100,50));
		set.add(new Point(200,100));
		set.add(new Point(300,150));
		
		assertTrue (set.contains(new Point(200,100)));
		
	}
	
	@Test
	void test_PriorityQueue_with_TreeNode() {
		fail("not implemented yet");
	}

}
