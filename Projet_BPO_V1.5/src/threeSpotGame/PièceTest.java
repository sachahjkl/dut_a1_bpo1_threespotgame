package threeSpotGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import threeSpotGame.Pièce.couleurPièce;

class PièceTest {

	@Test
	void testGetCouleur() {
		Pièce p = new Pièce(couleurPièce.R);
		assertTrue(p.getCouleur() == couleurPièce.R);
	}

}
