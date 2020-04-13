package threeSpotGame;

//import static org.junit.Assert.*;

//import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PlateauTest {

	@Test
	public void Plateau() {
		Plateau pl = new Plateau();
		assertTrue(pl.getPoints(Pièce.couleurPièce.R) == 0);
		assertTrue(pl.getPoints(Pièce.couleurPièce.B) == 0);
		assertEquals(pl.getPièce(0).getCouleur(), Pièce.couleurPièce.R);
		assertEquals(pl.getPièce(1).getCouleur(), Pièce.couleurPièce.W);
		assertEquals(pl.getPièce(2).getCouleur(), Pièce.couleurPièce.B);
	}

	@Test
	public void testGetNbMovs() {
		int MovAttendu = 3;
		Plateau pl = new Plateau();
		pl.scanPlateau(pl.getPièce(0));
		assertTrue(pl.getNbMovs() == MovAttendu);
	}

	@Test
	public void testChoixCoup() {
		Coup CoupAttendu = new Coup(0, 0, Coup.Direction.H);
		Plateau pl = new Plateau();
		pl.scanPlateau(pl.getPièce(0));
		assertTrue(Arrays.equals(CoupAttendu.getCoord(), pl.getCoup(1).getCoord()));
	}

	@Test
	public void testJouer() {
		Coup CoupAttendu = new Coup(0, 0, Coup.Direction.H);
		Plateau pl = new Plateau();
		pl.scanPlateau(pl.getPièce(0));
		pl.jouer(pl.getPièce(0), CoupAttendu);
		assertTrue(Arrays.equals(pl.getPièce(0).getCoord2(), new int[] { 0, 1 }));
	}

	@Test
	public void testScanPlateau() {
		Coup CoupAttendu1 = new Coup(0, 0, Coup.Direction.H);
		Coup CoupAttendu2 = new Coup(1, 0, Coup.Direction.V);
		Coup CoupAttendu3 = new Coup(2, 0, Coup.Direction.V);
		Plateau pl = new Plateau();
		for (int i = 1; i < 5; ++i) {
			assertEquals(pl.getCoup(i), null);
		}
		pl.scanPlateau(pl.getPièce(0));
		assertTrue(Arrays.equals(pl.getCoup(1).getCoord(), CoupAttendu1.getCoord()));
		assertEquals(pl.getCoup(1).getDir(), Coup.Direction.H);
		assertTrue(Arrays.equals(pl.getCoup(2).getCoord(), CoupAttendu2.getCoord()));
		assertEquals(pl.getCoup(2).getDir(), Coup.Direction.V);
		assertTrue(Arrays.equals(pl.getCoup(3).getCoord(), CoupAttendu3.getCoord()));
		assertEquals(pl.getCoup(3).getDir(), Coup.Direction.V);
	}

	@Test
	public void testAddPoints() {
		Plateau pl = new Plateau();
		for (int i = 0, p = 1; i < 10; i++, p++) {
			pl.addPoints(Pièce.couleurPièce.R);
			assertEquals(pl.getPoints(Pièce.couleurPièce.R), p);
			pl.addPoints(Pièce.couleurPièce.B);
			assertEquals(pl.getPoints(Pièce.couleurPièce.B), p);
		}
	}

	@Test
	public void testToString() {
		Plateau pl = new Plateau();
		System.out.println(pl.toString());
		System.out.println(pl.toString(pl.getPièce(0)));
	}

}
