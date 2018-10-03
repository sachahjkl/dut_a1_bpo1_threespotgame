package threeSpotGame;

public class Case {
	private boolean estSpot, estOccupé;
	private Pièce pièceQuiOccupe;
	public enum mov{O, V, H, HV};
	private mov m;
	private Case[] depl;
	
	public Case() {
		this.depl = new Case[2];
		this.m = mov.O;
		this.libérer();
	}
	public void setPièce(Pièce p) {
		this.occuper();
		this.pièceQuiOccupe = p;
	}
	public Pièce getPièce() {
		//ne pas retourner si la pièce est libre
		return this.pièceQuiOccupe;
	}
	public boolean estOccupée() {
		return this.estOccupé;
	}
	public void occuper() {
		this.estOccupé = true;
	}
	public void libérer() {
		this.estOccupé = false;
	}
	public void setSpot() {
		this.estSpot = true;
	}
	public boolean estSpot() {
		return this.estSpot;
	}
	public void setDéplacement(Case cV, Case cH) {
		depl[0] = cV;
		depl[1] = cH;
	}
	public void setMov(mov ms) {
		this.m = ms;
	}
	public mov getMov() {
		return this.m;
	}
	public Case getDepl(mov ms) {
		if(ms == mov.V)
			return this.depl[0];
		else if(ms == mov.H)
			return this.depl[1];
		return null;
	}
}
