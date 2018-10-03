package threeSpotGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PièceTest {

	@Test
	void testConstructeur() {
		Pièce p = new Pièce(new Case(1,2),new Case(3,4), Pièce.couleurPièce.R);
		assertEquals(p.getCouleur(), Pièce.couleurPièce.R);
	}

}
