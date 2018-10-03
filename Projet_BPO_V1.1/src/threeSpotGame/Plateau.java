package threeSpotGame;

public class Plateau {
	private final int SPOT = 3;
	private Case[][] plateau;

	public Plateau() {
		plateau = new Case[SPOT][SPOT];
		for(int i=0; i<SPOT; ++i) { //i = ligne
			for(int j=0; j<SPOT; ++j) { // j = colonne
				if (j == SPOT)
					plateau[i][j].devientSpot();
				else
					plateau[i][j].pasSpot();
				plateau[i][j].setCoord(i,j);
			}
		}
	}
	
	public static void afficher(String s){
		System.out.println(s);
	}
	public String plateauToString(){
		String s = "* * * * * * * * * * * * *";
		for(Case[] i : this.plateau) { //i = ligne
			s += "*\t*\t*\t*";
			for(Case j : i) { // j = colonne
				if (!j.estOccupée() && !j.estIlUnSpot()){
					s+= "*\t*";
				}
				if (!j.estOccupée() && !j.estIlUnSpot()){
					s+= "*\t*";
				}
				else if(!j.estOccupée() && j.estIlUnSpot()){
					s+= "*   O   *";	
				}
				else if(j.estOccupée()){
					s+= "*   "+j.getCouleur().toString()+"   *";
				}
				s +="*\t*\t*\t*\n";
			}
			s += "* * * * * * * * * * * * *";
		}
		return s;
	}
	public String mvmtToString(){
		
		return null;
	}
	public int[][] quelsMvmtPossibles(){
		int[][] listeMvmt = new int[9][2];
		int cmp = 0;
		for(int i=0; i<SPOT; ++i) { //i = ligne
			for(int j=0; j<SPOT; ++j) { // j = colonne
				if (j != 2 && !plateau[i][j+1].estOccupée()) {
					listeMvmt[cmp][0]=i;
					listeMvmt[cmp][1]=j;
					++cmp;
				}
				else if (i != 0 && !plateau[i-1][j].estOccupée()) {
					listeMvmt[cmp][0]=i;
					listeMvmt[cmp][1]=j;
					++cmp;
				}
			}
		}
		return listeMvmt;
	}

	

}

