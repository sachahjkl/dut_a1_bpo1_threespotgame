package threeSpotGame;

public class Pièce {
	public enum couleurPièce{R,W,B};
	private couleurPièce couleur;

	/**
	 * Creer une nouvelle pièce
	 * @param c La couleur à prendre par la pièce
	 */
	public Pièce(couleurPièce c) {
		this.couleur = c;
	}

	public couleurPièce getCouleur() {
		return this.couleur;
	}
}
