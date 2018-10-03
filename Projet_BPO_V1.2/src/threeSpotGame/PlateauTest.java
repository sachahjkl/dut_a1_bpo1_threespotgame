package threeSpotGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlateauTest {

	@Test
	void testConstructeur() {
		Plateau p = new Plateau();
		for(int i = 0; i<Plateau.SPOT;++i) {
			for (int j = 0; j<Plateau.SPOT; ++j) {
				if(j == Plateau.SPOT-1)
					assertTrue(p.getPlateau()[i][j].estSpot());
				else
					assertFalse(p.getPlateau()[i][j].estSpot());
			}
		}
	}

}
