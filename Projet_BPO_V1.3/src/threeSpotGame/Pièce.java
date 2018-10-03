package threeSpotGame;

public class Pièce {
	public enum couleurPièce{R, W, B};
	private int points;
	private couleurPièce couleur;
	
	public Pièce(couleurPièce c) {
		this.couleur = c;
	}
	
	public couleurPièce getCouleurPièce() {
		return couleur;
		
	}
	public void déplacerPièce(Plateau pl, Case c, Case.mov m) {
		this.libérerCases(pl);
		c.setPièce(this);
		c.getDepl(m).setPièce(this);
		if(c.estSpot())
			this.addPoint();
		if(c.getDepl(m).estSpot())
			this.addPoint();
	}
	private  void libérerCases(Plateau pl) {
		for(int i=0; i<Plateau.TAILLE;++i) {
			for (int j=0; j<Plateau.TAILLE;++j) {
				if(pl.getCase(i, j).getPièce().getCouleurPièce()==this.getCouleurPièce())
					pl.getCase(i, j).libérer();
			}
		}
	}
	
	private void addPoint() {
		this.points+=1;
	}
	public int getPoint() {
		return this.points;
	}
}
