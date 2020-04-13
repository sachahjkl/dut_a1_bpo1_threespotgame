package threeSpotGame;

/**
 * @author FROMENT Sacha, SOLEIMAN Agathe
 */

public class Coup {
	public enum Direction {
		V, H
	};

	private Direction dir;
	private int[] coords;

	/**
	 * @param i1 la ligne du coup.
	 * @param j1 la colonne du coup.
	 * @param d la direction du coup.
	 */
	public Coup(int i1, int j1, Direction d) {
		assert (i1 >= 0 && j1 >= 0);
		coords = new int[2];
		coords[0] = i1;
		coords[1] = j1;
		dir = d;
	}

	public Coup() {
		this(0, 0, Direction.H);
	}

	/**
	 * @return la direction d'un coup.
	 */
	public Direction getDir() {
		Direction r = this.dir;
		return r;
	}

	/**
	 * @return les coordonnées d'un coup.
	 */
	public int[] getCoord() {
		int[] r = new int[] { coords[0], coords[1] };
		return r;
	}

}
