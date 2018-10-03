package appli;
import threeSpotGame.*;

/**
 * @author FROMENT Sacha, SOLEIMAN Agathe
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Appli {
	private static Plateau p = new Plateau();
	private static Scanner sc = new Scanner(System.in);
	private static boolean finPartie = false;

	public static void main(String[] args) {

		while (true) {
			tour(p.getPièce(0));
			if (finPartie)
				break;
			tour(p.getPièce(1));
			tour(p.getPièce(2));
			if (finPartie)
				break;
			tour(p.getPièce(1));
		}
		if (p.getPoints(p.getPièce(0).getCouleur()) >= Plateau.SCORE_FIN_JEU) {
			if (p.getPoints(p.getPièce(2).getCouleur()) >= Plateau.SCORE_CONDITION)
				System.out.println("Le joueur Rouge a gagné");
			else
				System.out.println("Le joueur Bleu a gagné");
		} else if (p.getPoints(p.getPièce(2).getCouleur()) >= Plateau.SCORE_FIN_JEU) {
			if (p.getPoints(p.getPièce(0).getCouleur()) >= Plateau.SCORE_CONDITION)
				System.out.println("Le joueur Bleu a gagné");
			else
				System.out.println("Le joueur Rouge a gagné");
		}
		System.out.println("Score : Rouge=" + p.getPoints(p.getPièce(0).getCouleur()) + "; Bleu="
				+ p.getPoints(p.getPièce(2).getCouleur()) + "\n");
	}

	public static void tour(Pièce pi) {
		System.out.println(p.toString());
		System.out.println("Score : Rouge=" + p.getPoints(p.getPièce(0).getCouleur()) + "; Bleu="
				+ p.getPoints(p.getPièce(2).getCouleur()) + "\n");
		p.scanPlateau(pi);
		System.out.println(p.toString(pi));
		System.out.println("\nPièce " + pi.getCouleur() + ", choisissez un numéro parmi les coups possibles: ");
		int choix;
		while (true) {
			try {
				choix = sc.nextInt();
				if (choix > p.getNbMovs() || choix <= 0)
					throw new InputMismatchException();
				break;
			} catch (java.util.InputMismatchException e) {
				System.err.println("vous avez saisi une valeur incorrecte. Veuillez reessayer :");
				sc.nextLine();
			}
		}
		p.jouer(pi, p.getCoup(choix));
		if (p.getPoints(pi.getCouleur()) >= Plateau.SCORE_FIN_JEU)
			finPartie = true;
	}
}
