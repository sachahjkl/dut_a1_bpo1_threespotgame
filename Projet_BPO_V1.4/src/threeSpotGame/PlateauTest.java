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
	void testGetPièceEtSetPièce() {
		Plateau p = new Plateau();
		Pièce pi = new Pièce(couleurPièce.R);
		int c = 0;
		p.setPièce(pi, c, c); // mettre à l'emplacement 0, 0 la Pièce pi
		assertEquals(pi, p.getPièce(c, c));
	}
	
	@Test
	void testMouvPossibles()	{
		Plateau p = new Plateau();
		Pièce pi = new Pièce(couleurPièce.R);
		int resAttendu = 0, c = 0;
		assertEquals(p.mouvPossible(pi, c, c), resAttendu);	
	}
//	@Test
//	void testCalculsMouvements()	{
//		
//	}
}	
