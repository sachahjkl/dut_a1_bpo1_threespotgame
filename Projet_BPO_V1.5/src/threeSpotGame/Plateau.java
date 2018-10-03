package threeSpotGame;
/**
 *@author FROMENT Sacha & SOLEIMAN Agathe
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Plateau {
	public static final int SCORE_FIN_JEU = 2, SCORE_CONDITION = 1;
	public static final int SPOT = 3; // la taille du plateau et la colonne
	public static final Pièce pièceRouge = new Pièce(Pièce.couleurPièce.R);
	public static final Pièce pièceBlanche = new Pièce(Pièce.couleurPièce.W);
	public static final Pièce pièceBleue = new Pièce(Pièce.couleurPièce.B);
	private Pièce[][] p;
	private int[] points;

	/**
	 * @brief Initialise les attributs et place les pièces à la création d'un
	 *        plateau.
	 * @see initPlateau()
	 */
	public Plateau() {
		points = new int[2];
		points[0] = points[1] = 0;
		p = new Pièce[SPOT][SPOT];
		this.initPlateau();
	}

	/**
	 * établi le tableau à son état initial
	 */
	public void initPlateau() {
		this.points[0] = this.points[1] = 0;
		for (int i = 0; i < SPOT; ++i) {
			for (int j = 0; j < SPOT; ++j) {
				if (j >= SPOT - 2) {
					switch (i) {
					case 0:
						this.setPièce(pièceRouge, i, j);
						break;
					case 1:
						this.setPièce(pièceBlanche, i, j);
						break;
					case 2:
						this.setPièce(pièceBleue, i, j);
						break;
					}
				} else
					this.setPièce(null, i, j);
			}
		}
	}

	/**
	 * @brief Retourne une chaîne de caractères de l'état du plateau.
	 * @return String s : la chaîne représentant le plateau.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("Etat du plateau :\n\n");
		for (int i = 0; i < SPOT; ++i) {
			s.append("*");
			for (int n = 0; n < SPOT; ++n)
				s.append(" * * * *");
			s.append("\n");
			for (int l = 0; l < SPOT; ++l)
				s.append("*\t");
			s.append("*\n*");
			for (int j = 0; j < SPOT; ++j) {
				if (!this.estOccupée(i, j)) {
					if (j + 1 == SPOT)
						s.append("   O   *");
					else
						s.append("\t*");
				} else {
					s.append("   " + this.getPièce(i, j).getCouleur() + "   *");
				}
			}
			s.append("\n");
			for (int l = 0; l < SPOT; ++l)
				s.append("*\t");
			s.append("*\n");
		}
		s.append("*");
		for (int n = 0; n < SPOT; ++n)
			s.append(" * * * *");
		s.append("\n");
		return s.toString();
	}

	/**
	 * @brief Retourne une chaîne de caractères du plateau avec les numéros de
	 *        déplacement.
	 * @param Pièce
	 *            pi : la pièce qui est en train d'être jouée.
	 * @param int[][]
	 *            m : un tableau d'entier comptant les mouvements possibles pour
	 *            chaque case.
	 * @return String s : la chaîne représentant le plateau.
	 */
	public String toString(Pièce pi) {
		int[][] m = this.calculMouvements(pi);
		int cmp = 0;
		String c = new String();
		StringBuilder s = new StringBuilder("\n");
		for (int i = 0; i < SPOT; ++i) {
			s.append("*");
			for (int n = 0; n < SPOT; ++n)
				s.append(" * * * *");
			s.append("\n");
			for (int l = 0; l < SPOT; ++l)
				s.append("*\t");
			s.append("*\n*");
			for (int j = 0; j < SPOT; ++j) {
				if (!this.estOccupée(i, j) || this.getPièce(i, j) == pi) {
					if (j + 1 == SPOT && m[i][j] == 0)
						s.append("   O   *");
					else if (m[i][j] == 0)
						s.append("\t*");
					else if (m[i][j] == 1) {
						c = ++cmp + "";
						switch (c.length()) {
						case 1:
							s.append("   " + c + "   *");
							break;
						case 2:
							s.append("  " + c + "   *");
							break;
						case 3:
							s.append("  " + c + "  *");
							break;
						}
					} else if (m[i][j] == 2) {
						c = ++cmp + "-" + ++cmp;
						switch (c.length()) {
						case 3:
							s.append("  " + c + "  *");
							break;
						case 4:
							s.append("  " + c + " *");
							break;
						case 5:
							s.append(" " + c + " *");
							break;
						case 6:
							s.append("" + c + " *");
							break;
						case 7:
							s.append("" + c + "*");
							break;
						}
					}
				} else {
					s.append("   " + this.getPièce(i, j).getCouleur() + "   *");
				}
			}
			s.append("\n");
			for (int l = 0; l < SPOT; ++l)
				s.append("*\t");
			s.append("*\n");
		}
		s.append("*");
		for (int n = 0; n < SPOT; ++n)
			s.append(" * * * *");
		s.append("\n");
		return s.toString();
	}

	/**
	 * @brief Retourne le nombres de déplacements trouvés au total.
	 * @see mouvPossible(Pièce pi, int i, int j) & calculMouvements(Pièce pi)
	 * @param Pièce pi : la pièce qui est en train d'être jouée.
	 * @param int[][] m : un tableau d'entier comptant les mouvements possibles pour chaque case.         
	 * @return String s : la chaîne représentant le plateau.
	 */
	private int getNbMovs(Pièce pi) {
		int cmp = 0;
		for (int i = 0; i < SPOT; ++i) {
			for (int j = 0; j < SPOT; ++j) {
				if (!this.estOccupée(i, j) || this.getPièce(i, j) == pi) {
					if (this.calculMouvements(pi)[i][j] == 1)
						++cmp;
					else if (this.calculMouvements(pi)[i][j] == 2)
						cmp = cmp + 2;
				}
			}
		}
		return cmp;
	}

	/**
	 * @brief Retourne la pièce à l'emplacement (i,j) du plateau p.
	 * @param int i : la ligne du plateau.
	 * @param int j : la colonne du plateau.
	 * @return Pièce : la pièce trouvée aux coords (i,j).
	 */
	public Pièce getPièce(int i, int j) {
		return this.p[i][j];
	}

	/**
	 * @brief Retourne le nombre de déplacements trouvés pour une case du plateau
	 * @param Pièce pi : la pièce qui est en train d'être jouée.
	 * @param int i : la ligne du plateau.
	 * @param int j : la colonne du plateau.
	 * @return int : le nombre de déplacements trouvés.
	 */
	private int mouvPossible(Pièce pi, int i, int j) {
		int res = 0;
		if (i > 0 && this.mouvHautPossible(pi, i, j))
			++res;
		if (j < SPOT - 1 && this.mouvDroitPossible(pi, i, j))
			++res;
		return res;
	}

	/**
	 * @brief Retourne un tableau bidimensionnel stockant le nombre de déplacements
	 *        trouvés pour chaque case du plateau.
	 * @param Pièce pi : la pièce qui est en train d'être jouée.
	 * @return int[][] : un tableau d'entier comptant les mouvements possibles pour
	 *         chaque case.
	 */
	private int[][] calculMouvements(Pièce pi) {
		int[][] movs = new int[SPOT][SPOT];
		for (int i = 0; i < SPOT; ++i) {
			for (int j = 0; j < SPOT; ++j) {
				movs[i][j] = this.mouvPossible(pi, i, j);
			}
		}
		return movs;
	}

	/**
	 * @brief Vérifie qu'un mouvement vertical est possible depuis la case de coords
	 *        (i,j) lorsque on joue la pièce pi.
	 * @param Pièce pi : la pièce qui est en train d'être jouée.
	 * @param int i : la ligne du plateau.
	 * @param int j : la colonne du plateau.
	 * @return boolean : mouvement possible(true) ou impossible(false).
	 */
	private boolean mouvHautPossible(Pièce pi, int i, int j) {
		assert (i > 0);
		return ((!this.estOccupée(i, j) && (this.getPièce(i - 1, j) == pi || !this.estOccupée(i - 1, j)))
				|| (this.getPièce(i, j) == pi && !this.estOccupée(i - 1, j)));
	}

	/**
	 * @brief Vérifie qu'un mouvement horizontal est possible depuis la case de
	 *        coords (i,j). lorsque on joue la pièce pi.
	 * @param Pièce pi : la pièce qui est en train d'être jouée.
	 * @param int i : la ligne du plateau.
	 * @param int j : la colonne du plateau.
	 * @return boolean : mouvement possible(true) ou impossible(false).
	 */
	private boolean mouvDroitPossible(Pièce pi, int i, int j) {
		assert (j < 2);
		return ((!this.estOccupée(i, j) && (this.getPièce(i, j + 1) == pi || !this.estOccupée(i, j + 1)))
				|| (this.getPièce(i, j) == pi && !this.estOccupée(i, j + 1)));
	}

	/**
	 * @brief Vérifie que la case aux coords (i,j) est occupée par une pièce.
	 * @param int i : la ligne du plateau.
	 * @param int j : la colonne du plateau.
	 * @return boolean : case occupée(true) ou libre(false).
	 */
	private boolean estOccupée(int i, int j) {
		return this.getPièce(i, j) != null;
	}

	/**
	 * @brief Place la pièce aux coords (i,j).
	 * @param int i : la ligne du plateau.
	 * @param int j : la colonne du plateau.
	 * @return boolean : case occupée(true) ou libre(false).
	 */
	private void setPièce(Pièce pi, int i, int j) {
		this.p[i][j] = pi;
	}

	/**
	 * @brief Retourne le nombre de points du joueur demandé.
	 * @param int numJoueur: le numéro du joueur (1 ou 2) moins 1.
	 * @return int : le nombre de points du joueur.
	 */
	public int getPoints(int numJoueur) {
		return points[numJoueur];
	}

	/**
	 * @brief Retourne le nombre de points du joueur demandé.
	 * @param pièce : la pièce que joue le joueur dont on veut augmenter les points.
	 */
	private void incPoints(Pièce pi) {
		switch (pi.getCouleur()) {
		case R:
			++this.points[0];
			break;
		case B:
			++this.points[1];
			break;
		default:
			break;
		}
	}

	/**
	 * @brief Joue un tour du jeu.
	 * @param Pièce pi : la pièce qui est en train d'être jouée.
	 */
	public void jouer(Pièce pi) {
		int choix = 0;
		System.out.println(this.toString());
		System.out
				.println("Points Joueur Rouge = " + this.getPoints(0) + "; Points Joueur Bleu = " + this.getPoints(1));
		int cmp = this.getNbMovs(pi);
		System.out.println(this.toString(pi) + "\n");
		switch (pi.getCouleur()) {
		case R:
			System.out.println("Le joueur de la pièce Rouge saisit une case de déplacement : ");
			choix = lecture(cmp);
			break;
		case B:
			System.out.println("Le joueur de la pièce Bleue saisit une case de déplacement : ");
			choix = lecture(cmp);
			break;
		case W:
			System.out.println("Le joueur qui vient de jouer saisit une case de déplacement pour la pièce blanche : ");
			choix = lecture(cmp);
			break;
		default:
			break;
		}
		System.out.println("\nChoix Correct\n");
		this.poserPièce(pi, choix, this.calculMouvements(pi));
	}

	/**
	 * @brief lie et vérifie les entrées.
	 * @param int cmp : le nombre de déplacements possibles trouvés pour ce tour.
	 * @see getNbMovs().
	 */
	private int lecture(int cmp) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				int choix = sc.nextInt();
				if (choix > cmp || choix <= 0)
					throw new InputMismatchException();
				return choix;
			} catch (java.util.InputMismatchException e) {
				System.err.println("vous avez saisi une valeur incorrecte. Veuillez reessayer :");
				sc.nextLine();
			}
		}
	}

	/**
	 * @brief Retire toute une pièce du plateau p.
	 * @param Pièce pi : la pièce qui est en train d'être retirée.
	 */
	private void retirerPièce(Pièce pi) {
		for (int i = 0; i < SPOT; ++i) {
			for (int j = 0; j < SPOT; ++j) {
				if (this.getPièce(i, j) == pi)
					this.setPièce(null, i, j);
			}
		}
	}

	/**
	 * @brief Pose la pièce.
	 * @param Pièce pi : la pièce qui est en train d'être posée.
	 * @param int choix : le déplacement choisi par le joueur.
	 * @param int[][] : le nb de déplacements possible par cases.
	 */
	private void poserPièce(Pièce pi, int choix, int[][] m) {
		int res = 0;
		for (int i = 0; i < SPOT; ++i) {
			for (int j = 0; j < SPOT; ++j) {
				if (i > 0 && m[i][j] > 0 && this.mouvHautPossible(pi, i, j)) {
					++res;
					if (res == choix) {
						this.retirerPièce(pi);
						this.setPièce(pi, i, j);
						this.setPièce(pi, i - 1, j);
						if (j + 1 == SPOT) {
							this.incPoints(pi);
							this.incPoints(pi);
						}
						return;
					}
				}

				if (j < SPOT - 1 && m[i][j] > 0 && this.mouvDroitPossible(pi, i, j)) {
					++res;
					if (res == choix) {
						this.retirerPièce(pi);
						this.setPièce(pi, i, j);
						this.setPièce(pi, i, j + 1);
						if (j == SPOT - 2) {
							this.incPoints(pi);
						}
						return;
					}
				}
			}
		}
	}
}
