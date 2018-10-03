package threeSpotGame;

import java.util.Scanner;

public class Appli {
	private Pièce pièce_R, pièce_W, pièce_B;
	private final int SCORE_FIN_JEU = 12, SCORE_CONDITION = 6;
	private Plateau plateau;
	private Scanner c;
	public Appli() {
		plateau = new Plateau(Plateau.TAILLE);
		pièce_R = new Pièce(Pièce.couleurPièce.R);
		pièce_W = new Pièce(Pièce.couleurPièce.W);
		pièce_B = new Pièce(Pièce.couleurPièce.B);
		c = new Scanner(System.in);
	}
	public void lancerJeu() {
		
		while(pièce_R.getPoint() < SCORE_FIN_JEU && pièce_B.getPoint() < SCORE_FIN_JEU) {
			jouer(pièce_R);
			jouer(pièce_W);
			jouer(pièce_B);
			jouer(pièce_W);
		}
		if(pièce_R.getPoint() == SCORE_FIN_JEU) {
			if(pièce_B.getPoint() >= SCORE_CONDITION) 
				System.out.println("Le joueur 1 a gagné");
			else
				System.out.println("Le joueur 2 a gagné");
		}
		else if(pièce_B.getPoint() == SCORE_FIN_JEU) {
			if(pièce_R.getPoint() >= SCORE_CONDITION) 
				System.out.println("Le joueur 2 a gagné");
			else
				System.out.println("Le joueur 1 a gagné");
		}
		
	}
	public void jouer(Pièce pi) {
		plateau.
		
	}
}
