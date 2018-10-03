package threeSpotGame;

public class Plateau {
	public static final int SPOT = 3;
	private Case[][] plateau;

	public Plateau() {
		plateau = new Case[SPOT][SPOT];
		for(int i=0; i<SPOT; ++i) { //i = ligne
			for(int j=0; j<SPOT; ++j) { // j = colonne
				plateau[i][j] = new Case(i,j);
				if (j == SPOT-1)
					plateau[i][j].setSpot();
			}
		}
	}
	public Case[][] getPlateau() {
		return plateau;
	}
	public Case getCase(int l, int c) {
		return this.plateau[l][c];
	}
	public static void afficher(String s){
		System.out.println(s);
	}
	public String plateauToString(Pièce p){
		String s = "* * * * * * * * * * * * *\n";
		for(Case[] i : this.plateau) { //i = ligne
			s += "*\t*\t*\t*\n";
			for(Case j : i) { // j = colonne
				s+="*"
				if (!j.estOccupée() && !j.estSpot()){
					s+= "\t";
				}
				else if(!j.estOccupée() && j.estSpot()){
					s+= "*   O   *";	
				}
				else if(j.estOccupée()){
					s+= "   "+p.getCouleur().toString()+"   ";
				}
				s +="*\t*\t*\t*\n";
			}
			s += "* * * * * * * * * * * * *";
		}
		return s;
	}
	public Mouvement[] quellesCasesLibres(Pièce p) {
		Mouvement[] movs= new Mouvement[9];
		int cmp = 0;
		p.libérerPièce();
		for(int i=0; i<SPOT; ++i) { //i = ligne
			for(int j=0; j<SPOT; ++j) { // j = colonne
				if(!this.getPlateau()[i][j].estOccupée() && !this.caseHautOccupée(i, j)) {
					 movs[cmp][0]= this.getCase(i, j);
				}
				if(!this.getPlateau()[i][j].estOccupée() && !this.caseDroiteOccupée(i, j)) {
					 movs[cmp][1]= this.getCase(i, j);
				}
			
				++cmp;
			}
		}
		return movs;
	}
	private boolean caseHautOccupée(int l, int c) {
		return l > 0 && !this.plateau[l-1][c].estOccupée();
	}
	private boolean caseDroiteOccupée(int l, int c) {
		return c < 2 && !this.plateau[l][c+1].estOccupée();
	}
}
