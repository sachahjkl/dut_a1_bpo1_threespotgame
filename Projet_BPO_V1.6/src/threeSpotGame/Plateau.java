package threeSpotGame;

/**
 * @author FROMENT Sacha, SOLEIMAN Agathe
 */

import java.util.Arrays;
import java.util.HashMap;

import threeSpotGame.Pièce.couleurPièce;

public class Plateau {
	public static final int SCORE_FIN_JEU = 12, SCORE_CONDITION = 6;
	public static final int SPOT = 3; // la taille du plateau et la colonne
	private int[] points;
	private Pièce[] p;
	private HashMap<Integer, Coup> coupsPossibles;
	private int cmpHash;

	/**
	 * @brief initialise le plateau à l'état de départ du jeu.
	 */
	public Plateau() {
		coupsPossibles = new HashMap<Integer, Coup>(4);
		for (int i = 1; i < 5; coupsPossibles.put(i, null), i++);
		p = new Pièce[3];
		points = new int[] { 0, 0 };
		p[0] = new Pièce(0, 1, couleurPièce.R);
		p[1] = new Pièce(1, 1, couleurPièce.W);
		p[2] = new Pièce(2, 1, couleurPièce.B);
	}

	/**
	 * @brief Incrémente de 1 les points d'une pièce
	 * @param c couleur de la pièce dont on veut incrémenter les points.
	 */
	public void addPoints(couleurPièce c) {
		switch (c) {
		case R:
			++points[0];
			break;
		case B:
			++points[1];
			break;
		default:
			break;
		}
	}

	/**
	 * @brief Retourne le nombre de points d'une pièce.
	 * @param c couleur de la pièce dont on veut récupérer les points.
	 * @return int : le nombre de points de la pièce.
	 */
	public int getPoints(couleurPièce c) {
		switch (c) {
		case R:
			return points[0];
		case B:
			return points[1];
		default:
			return 0;
		}
	}

	/**
	 * @brief retourne une chaîne de caractères représentant l'état du plateau de jeu.
	 * @return String : la chaîne de caractères.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Etat du plateau :\n\n* * * * * * * * * * * * *\n");
		for (int i = 0; i < SPOT; i++) {
			s.append("*\t*\t*\t*\n*");
			for (int j = 0; j < SPOT; j++) {
				if (estOccupé(i, j) != null) {
					s.append("   " + estOccupé(i, j).getCouleur() + "   *");
					continue;
				}
				if (j == SPOT - 1) {
					s.append("   O   *");
					continue;
				}
				s.append("\t*");
			}
			s.append("\n*\t*\t*\t*\n* * * * * * * * * * * * *\n");
		}
		return s.toString();
	}

	/**
	 * @brief retourne une chaîne de caractères représentant les déplacements possibles pour une pièce.
	 * @param pi : la pièce en train d'être jouée.
	 * @return String : la chaîne de caractères.
	 */
	public String toString(Pièce pi) {
		int movs = 0;
		StringBuilder s = new StringBuilder();
		s.append("Deplacements possibles :\n\n* * * * * * * * * * * * *\n");
		for (int i = 0; i < SPOT; i++) {
			s.append("*\t*\t*\t*\n*");
			for (int j = 0; j < SPOT; j++) {
				int cmp = 0;
				if (estOccupé(i, j) != null && !estOccupé(i, j).equals(pi)) {
					s.append("   " + estOccupé(i, j).getCouleur() + "   *");
					continue;
				}
				for (int j2 = 1; coupsPossibles.get(j2) != null; ++j2) {
					if (Arrays.equals(coupsPossibles.get(j2).getCoord(), new int[] { i, j })) {
						++movs;
						++cmp;
					}
				}
				if (cmp == 2) {
					s.append("  " + (movs - 1) + "-" + movs + "  *");
					continue;
				}
				if (cmp == 1) {
					s.append("   " + movs + "   *");
					continue;
				}
				if (j == SPOT - 1) {
					s.append("   O   *");
					continue;
				}
				s.append("\t*");
			}
			s.append("\n*\t*\t*\t*\n* * * * * * * * * * * * *\n");
		}
		return s.toString();
	}

	/**
	 * @brief retourne le nombre de mvmts trouvés pour une pièce.
	 * @return int : le nombre de mvmts trouvés.
	 */
	public int getNbMovs(){ return this.cmpHash;}

	/**
	 * @brief retourne la pièce, si il y en a une, qui occupe la case aux coords i,j du plateau.
	 * @param i la ligne du plateau.
	 * @param j la colonne du plateau.
	 * @return Pièce : la pièce trouvée.
	 */
	private Pièce estOccupé(int i, int j) {
		for (Pièce pi : p) {
			if (Arrays.equals(pi.getCoord1(), new int[] { i, j }) || Arrays.equals(pi.getCoord2(), new int[] { i, j }))
				return pi;
		}
		return null;
	}

	private Coup verifCoupV(Pièce pi, int i, int j) {
		return verifCoup(pi, i, j, 1, 0, Coup.Direction.V);
	}

	private Coup verifCoupH(Pièce pi, int i, int j) {
		return verifCoup(pi, i, j, 0, 1, Coup.Direction.H);
	}

	/**
	 * @param pi
	 * @param i la ligne du plateau.
	 * @param j la colonne du plateau.
	 * @param ip 1 ou 0 si l'ont veut ou non trouver les mouvs verticaux.
	 * @param jp 1 ou 0 si l'ont veut ou non trouver les mouvs horizontaux.
	 * @return le coup trouvé.
	 */
	private Coup verifCoup(Pièce pi, int i, int j, int ip, int jp, Coup.Direction d) {
		assert (i >= 0 && j >= 0);
		if (estOccupé(i, j) == null && estOccupé(i - ip, j + jp) == null
				|| estOccupé(i, j) == null && estOccupé(i - ip, j + jp).equals(pi)
				|| estOccupé(i - ip, j + jp) == null && estOccupé(i, j).equals(pi)) {
			return new Coup(i, j, d);
		}
		return null;
	}


	/**
	 * @brief joue le coup donné pour la pièce donné
	 * @param pi : la pièce à jouer.
	 * @param c : le coup à jouer.
	 */
	public void jouer(Pièce pi, Coup c) {
		if (c.getDir() == Coup.Direction.V) {
			if (c.getCoord()[1] == SPOT - 1) {
				addPoints(pi.getCouleur());
				addPoints(pi.getCouleur());
			}
			pi.setCoordVertical(c.getCoord()[0], c.getCoord()[1]);
		} else if (c.getDir() == Coup.Direction.H) {
			if (c.getCoord()[1] == SPOT - 2)
				addPoints(pi.getCouleur());
			pi.setCoordHorizontal(c.getCoord()[0], c.getCoord()[1]);
		}
	}

	/**
	 * @param i : le numéro de la pièce voulue (0: R; 1: W; 2: B).
	 * @return la pièce trouvée.
	 */
	public Pièce getPièce(int i) {
		assert (i >= 0 && i < 3);
		return p[i];
	}

	/**
	 * @brief retourn le coup de clé i.
	 * @param i : la clé du coup voulu.
	 * @return le coup associé à la clé i.
	 */
	public Coup getCoup(int i) {
		assert (i > 0 && i < 5);
		return coupsPossibles.get(i);
	}

	/**
	 * @brief scanne les coups possibles pour la pièce pi sur le plateau.
	 * @param pi : la pièce dont on veut scanner les coups.
	 */
	public void scanPlateau(Pièce pi) {
		cmpHash = 0;
		for (int i = 0; i < SPOT; i++) {
			for (int j = 0; j < SPOT; j++) {
				if (i > 0 && verifCoupV(pi, i, j) != null) {
					++cmpHash;
					coupsPossibles.put(cmpHash, verifCoupV(pi, i, j));
				}
				if (j < SPOT - 1 && verifCoupH(pi, i, j) != null) {
					++cmpHash;
					coupsPossibles.put(cmpHash, verifCoupH(pi, i, j));
				}
			}
		}
		for (int i = cmpHash + 1; i <= coupsPossibles.size(); ++i) {
			coupsPossibles.put(i, null);
		}
	}
}
