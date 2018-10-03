package threeSpotGame;

public class Case {
	private boolean occupée, estSpot;
	private int[] coord = new int[2];

	public Case() {
		
	}
	public void setCoord(int x, int y) {
		this.coord[0] = x;
		this.coord[1] = y;
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

}
