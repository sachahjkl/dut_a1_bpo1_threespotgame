package threeSpotGame;

public class Case {
	private boolean occupée, estSpot;
	private int[] coord; // coord[0] = ligne; coord[1] = colonne
	public enum nbMovs{A, B, C}; // A = 1 déplacement vertical; B = 1 déplacement horizontal; C = Les deux
	public Mouvement[] movs;
	public Case(int l, int c) {
		coord = new int[2];
		this.setCoord(l, c);
	}
	private void setCoord(int l, int c) {
		this.coord[0]=l;
		this.coord[1]=c;
	}
	public int[] getCoord() {
		return this.coord;
	} 
	public boolean estOccupée() {
		return this.occupée;
	}
	public void libérer() {
		this.occupée = false;
	}
	public void occuper() {
		this.occupée = true;
	}
	public boolean estSpot() {
		return estSpot;
	}
	public void setSpot() {
		this.estSpot = true;
	}
	public void setDéplacements() {
		
	}
	

}
