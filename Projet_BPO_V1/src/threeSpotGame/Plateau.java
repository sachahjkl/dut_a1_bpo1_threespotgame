package threeSpotGame;

public class Plateau {
	private final int COL_SPOT = 3;
	private Case[][] plateau = new Case[3][3] ;

	public Plateau() {
		for(int i=0; i<3; ++i) {
			for(int j=0; j<3; ++j) {
				if (i == COL_SPOT)
					plateau[i][j].devientSpot();
				else
					plateau[i][j].pasSpot();
				plateau[i][j].setCoord(i,j);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
