package threeSpotGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaseTest {

	@Test
	void testConstructeur() {
		final int NB = 10;
		Case[] c = new Case[NB];
		for(int i=0; i<NB; ++i) {
			c[i] = new Case(i,i+1);
			assertEquals(c[i].getCoord()[0], i);
			assertEquals(c[i].getCoord()[1], i+1);
		}
	}
	@Test
	void testOccuper() {
		Case c = new Case(1,2);
		c.occuper();
		assertTrue(c.estOccupée());
		c.libérer();
		assertFalse(c.estOccupée());
	}
	@Test
	void testSpot() {
		Case c = new Case(1,2);
		c.setSpot();
		assertTrue(c.estSpot());
	}

}
