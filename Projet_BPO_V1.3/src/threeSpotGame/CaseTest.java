package threeSpotGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaseTest {

	@Test
	void testLibérer() {
		Case c = new Case();
		c.libérer();
		assert(c.estOccupée());
	}
}
