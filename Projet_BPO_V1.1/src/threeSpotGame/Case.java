package threeSpotGame;

public class Case {
	private boolean occupée, estSpot;
	private int[] coord; // coord[0] = ligne; coord[1] = colonne
	public enum couleurPièce {B, R, W};
	private couleurPièce couleur;

	public Case() {
		coord = new int[2];
	}
	public void setCoord(int l, int c) {
		this.coord[0] = l;
		this.coord[1] = c;
	}
	public int[] getCoord() {
		return this.coord;
	}
	public boolean estOccupée() {
		return occupée;
	}

	public void libérer() {
		this.occupée = false;
	}
	public void occuper() {
		this.occupée = true;
	}
	public boolean estIlUnSpot() {
		return estSpot;
	}

	public void devientSpot() {
		this.estSpot = true;
	}
	public void pasSpot() {
		this.estSpot = false;
	}
	public couleurPièce getCouleur() {
		return this.couleur;
	}
	public boolean verifMvmtHorizontal(){
		
		return true;
	}
	public boolean verifMvmtVertical(){
		
		return true; 
	}
	

}
