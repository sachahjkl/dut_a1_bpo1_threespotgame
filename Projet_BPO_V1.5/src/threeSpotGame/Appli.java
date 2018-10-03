package threeSpotGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Appli {
	private static Plateau plateau;

	public static void main(String[] args) {
		plateau = new Plateau();
		Scanner sc = new Scanner(System.in);
		lancerJeu(plateau);
		while (true) {
			System.out.println("Voulez-vous rejouer : Oui(O)/Non(N)?");
			System.out.println("\n");
			if (répondre(sc))
				rejouer(plateau);
			else
				break;
		}
		sc.close();
	}

	/**
	 * @brief Demande à l'utilisateur de répondre Oui ou Non à une question
	 * @param Scanner
	 *            sc : le scanner de saisie
	 */
	private static boolean répondre(Scanner sc) {
		String choix;
		while (true) {
			try {
				if (!(sc.hasNext("o") || sc.hasNext("O") || sc.hasNext("n") || sc.hasNext("N"))) {
					throw new InputMismatchException(
							"Vous n'avez pas fait un choix correct." + " Reessayez Oui(0)/Non(N) :");
				} else {
					choix = sc.next().toUpperCase();
					sc.nextLine();
					switch (choix) {
					case "O":
						return true;
					case "N":
						return false;
					}
				}
					
			} catch (InputMismatchException e) {
				System.err.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

	/**
	 * @brief la boucle de jeu avec l'affichage du gagnant.
	 * @param Plateau
	 *            p : le plateau de jeu
	 */
	public static void lancerJeu(Plateau p) {
		while (true) {
			p.jouer(Plateau.pièceRouge);
			if (p.getPoints(0) >= Plateau.SCORE_FIN_JEU)
				break;
			p.jouer(Plateau.pièceBlanche);
			p.jouer(Plateau.pièceBleue);
			if (p.getPoints(1) >= Plateau.SCORE_FIN_JEU)
				break;
			p.jouer(Plateau.pièceBlanche);
		}
		if (p.getPoints(0) >= Plateau.SCORE_FIN_JEU) {
			if (p.getPoints(1) >= Plateau.SCORE_CONDITION)
				System.out.println("Le joueur Rouge a gagné");
			else
				System.out.println("Le joueur Bleu a gagné");
		} else if (p.getPoints(1) >= Plateau.SCORE_FIN_JEU) {
			if (p.getPoints(0) >= Plateau.SCORE_CONDITION)
				System.out.println("Le joueur Bleu a gagné");
			else
				System.out.println("Le joueur Rouge a gagné");
		}
		System.out.println("Score : Rouge=" + p.getPoints(0) + "; Bleu=" + p.getPoints(1) + "\n");
	}

	public static void rejouer(Plateau p) {
		p.initPlateau();
		lancerJeu(p);
	}
}
