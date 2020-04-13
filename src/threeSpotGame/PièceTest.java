package threeSpotGame;

//import static org.junit.Assert.*;

//import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class PièceTest {

	@Test
	public void testPièce() {
		Pièce p1 = new Pièce();
		Pièce p2 = new Pièce(0, 0, Pièce.couleurPièce.W);
		assertTrue(Arrays.equals(p1.getCoord1(), p2.getCoord1()));
		assertTrue(Arrays.equals(p1.getCoord2(), p2.getCoord2()));
		assertEquals(p1.getCouleur(), p2.getCouleur());
	}

}
