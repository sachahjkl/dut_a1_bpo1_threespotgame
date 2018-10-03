package threeSpotGame;

public class Jeu {
	private Pièce pièce_R, pièce_W, pièce_B;
	private int points_1 ,points_2;
	private final int SCORE_FIN_JEU = 12, SCORE_CONDITION = 6;
	private Plateau p;
	public Jeu() {
		p = new Plateau();
		pièce_R = new Pièce(p.getCase(0,1),p.getCase(0,2), Pièce.couleurPièce.R);
		pièce_W = new Pièce(p.getCase(1,1),p.getCase(1,2), Pièce.couleurPièce.W);
		pièce_B = new Pièce(p.getCase(2,1),p.getCase(2,2), Pièce.couleurPièce.B);
	}
	public void lancerJeu() {
		
		while(points_1 < SCORE_FIN_JEU && points_2 < SCORE_FIN_JEU) {
			jouer(pièce_R);
			jouer(pièce_W);
			jouer(pièce_B);
			jouer(pièce_W);
		}
		if(points_1 == SCORE_FIN_JEU) {
			if(points_2 >= SCORE_CONDITION) 
				System.out.println("Le joueur 1 a gagné");
			else
				System.out.println("Le joueur 2 a gagné");
		}
		else if(points_2 == SCORE_FIN_JEU) {
			if(points_1 >= SCORE_CONDITION) 
				System.out.println("Le joueur 2 a gagné");
			else
				System.out.println("Le joueur 1 a gagné");
		}
	}
	public void jouer(Pièce pi) {
		p.plateauToString(pi);
		Case[][] casesLibres = p.quellesCasesLibres(pi);
		choixDéplacement(p.quellesCasesLibres(pi) , 3);
	}
	public void choixDéplacement(Case[][] movs, int choix) {
		
	}

}
