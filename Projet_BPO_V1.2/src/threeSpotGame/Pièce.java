package threeSpotGame;

public class Pièce {
	private Case[] c;
	public enum couleurPièce {B, R, W};
	private couleurPièce couleur;
	Pièce.couleurPièce.B;
	
	public Pièce(Case c0, Case c1, couleurPièce coul) {
		c = new Case[2];
		c[0] = c0 ;
		c[1] = c1;
		couleur = coul;
	}
	public int[][]getPosPièce(){
		int [][] coord = new int[2][2];
		coord[0] = c[0].getCoord();
		coord[1] = c[1].getCoord();
		return coord;
	}
	public couleurPièce getCouleur() {
		return this.couleur;
	}
	public void setCase(Case c0, Case c1) {
		c[0] = c0;
		c[1] = c1;
		c[0].occuper();
		c[1].occuper();
	}
	public Case[] getCase() {
		return c;
		
	}
	public void libérerPièce() {
		this.getCase()[0].libérer();
		this.getCase()[1].libérer();
	}
	
}
