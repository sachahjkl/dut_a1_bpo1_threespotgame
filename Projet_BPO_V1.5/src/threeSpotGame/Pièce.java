package threeSpotGame;

public class Pièce {
	public enum couleurPièce {
		R, W, B
	};

	private couleurPièce couleur;

	/**
	 * Creer une nouvelle pièce.
	 * 
	 * @param c
	 *            La couleur à prendre par la pièce.
	 */
	public Pièce(couleurPièce c) {
		this.couleur = c;
	}

	/**
	 * @brief Retourne la couleur de la pièce.
	 * @return couleurPièce : la couleur de la pièce.
	 */
	public couleurPièce getCouleur() {
		return this.couleur;
	}
}
