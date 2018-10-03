package threeSpotGame;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import threeSpotGame.Pièce.couleurPièce;

class PlateauTest {

	@Test
	void testInitPlateau() {
		Plateau p = new Plateau();
		for (int i = 0; i < Plateau.SPOT; ++i) {
			for (int j = 0; j < Plateau.SPOT; ++j) {
				if (j == 0)
					assertEquals(p.getPièce(i, j), null);
				else {
					switch (i) {
					case 0:
						assertEquals(p.getPièce(i, j).getCouleur(), couleurPièce.R);
						break;
					case 1:
						assertEquals(p.getPièce(i, j).getCouleur(), couleurPièce.W);
						break;
					case 2:
						assertEquals(p.getPièce(i, j).getCouleur(), couleurPièce.B);
						break;
					}
				}
			}
		}
	}

	@Test
	void testGetPièce() {
		Plateau p = new Plateau();
		assertEquals(Plateau.pièceRouge, p.getPièce(0, Plateau.SPOT - 1));
		assertEquals(Plateau.pièceBlanche, p.getPièce(1, Plateau.SPOT - 1));
		assertEquals(Plateau.pièceBleue, p.getPièce(2, Plateau.SPOT - 1));
	}

}
