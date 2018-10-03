package threeSpotGame;

import threeSpotGame.Case.couleurPièce;

public class Jeu {
	private Plateau p;
	private int compteurP1, compteurP2;
	private final int SCORE_FIN_JEU = 12, SCORE_CONDITION = 6;
	
	public Jeu() {
		p = new Plateau();
		compteurP1 = compteurP2 = 0;
	}
	
	public void lancerJeu() {
		
		while(compteurP1 < SCORE_FIN_JEU && compteurP2 < SCORE_FIN_JEU) {
			jouer(couleurPièce.R);
			jouer(couleurPièce.W);
			jouer(couleurPièce.B);
			jouer(couleurPièce.W);
		}
		if(compteurP1 == SCORE_FIN_JEU) {
			if(compteurP2 >= SCORE_CONDITION) 
				System.out.println("Le joueur 1 a gagné");
			else
				System.out.println("Le joueur 2 a gagné");
		}
		else if(compteurP2 == SCORE_FIN_JEU) {
			if(compteurP1 >= SCORE_CONDITION) 
				System.out.println("Le joueur 2 a gagné");
			else
				System.out.println("Le joueur 1 a gagné");
		}
		
	}
	public void jouer(couleurPièce c) {
		Plateau.afficher(p.plateauToString());
		p.quelsMvmtPossibles();
	}
	
	public static void main(String[] args) {

	}
	
}
