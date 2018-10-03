package threeSpotGame;

//import static org.junit.Assert.*;

//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class CoupTest {

	@Test
	public void testCoup() {
		Coup c1 = new Coup();
		Coup c2 = new Coup(0,0, Coup.Direction.H);
		assertTrue(Arrays.equals(c1.getCoord(),c2.getCoord()));
		assertEquals(c1.getDir(),c2.getDir());
	}
	

}
