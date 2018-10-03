package threeSpotGame;

/**
 * @author FROMENT Sacha, SOLEIMAN Agathe
 */

public class Pièce {
	public enum couleurPièce {
		R, W, B
	};

	private couleurPièce couleur;
	private int[] coords;

	/**
	 * @param i1 la ligne ou se trouve la pièce. 
	 * @param j1 la colonne ou se trouve la partie gauche de la pièce.
	 * @param c la couleur de la pièce.
	 */
	public Pièce(int i1, int j1, couleurPièce c) {
		assert (i1 >= 0 && j1 >= 0);
		coords = new int[4];
		coords[0] = i1;
		coords[1] = j1;
		coords[2] = i1;
		coords[3] = j1 + 1;
		couleur = c;
	}

	public Pièce() {
		this(0, 0, couleurPièce.W);
	}

	/**
	 * @brief établi les coordonnées d'une pièce.
	 * @param i1 la ligne ou se trouve la partie basse de la pièce.
	 * @param j1 la colonne ou se trouve la partie basse de la pièce.
	 * @param i2 la ligne ou se trouve la partie haute de la pièce.
	 * @param j2 la colonne ou se trouve la partie basse de la pièce.
	 */
	private void setCoord(int i1, int j1, int i2, int j2) {
		assert (i1 >= 0 && j1 >= 0);
		coords[0] = i1;
		coords[1] = j1;
		coords[2] = i2;
		coords[3] = j2;
	}

	/**
	 *@brief pose la pièce verticalement aux coords i1,j1.
	 */
	public void setCoordVertical(int i1, int j1) {
		this.setCoord(i1, j1, i1 - 1, j1);
	}

	/**
	 *@brief pose la pièce horizontalement aux coords i1,j1.
	 */
	public void setCoordHorizontal(int i1, int j1) {
		this.setCoord(i1, j1, i1, j1 + 1);
	}


	/**
	 * @return les coords i et j de la partie basse de la pièce.
	 */
	public int[] getCoord1() {
		int[] r = new int[] { coords[0], coords[1] };
		return r;
	}

	/**
	 * @return les coords i et j de la partie haute de la pièce.
	 */
	public int[] getCoord2() {
		int[] r = new int[] { coords[2], coords[3] };
		return r;
	}

	/**
	 * @return la couleur de la pièce.
	 */
	public couleurPièce getCouleur() {
		couleurPièce c = couleur;
		return c;
	}

}
