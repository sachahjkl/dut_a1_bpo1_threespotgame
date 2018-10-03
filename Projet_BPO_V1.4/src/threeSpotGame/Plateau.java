package threeSpotGame;

import java.util.Scanner;

public class Plateau {
	private final int SCORE_FIN_JEU = 12, SCORE_CONDITION = 6;
	public static final int SPOT = 3;
	private Pièce pièceRouge;
	private Pièce pièceBlanche;
	private Pièce pièceBleue;
	private Pièce[][] p;
	private int[] points;

	public Plateau() {
		pièceRouge = new Pièce(Pièce.couleurPièce.R);
		pièceBlanche = new Pièce(Pièce.couleurPièce.W);
		pièceBleue = new Pièce(Pièce.couleurPièce.B);
		points = new int[2];
		points[0]=points[1]=0;
		p = new Pièce[SPOT][SPOT];
		this.initPlateau();
	}
	
	/**
	 * établi le tableau à son état initial
	*/
	public void initPlateau() {
		for(int i=0; i < SPOT;++i) {
			for(int j=0; j<SPOT;++j) {
				if(j > 0) {
					switch(i) {
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
				}
				else
					this.setPièce(null, i, j);
			}
		}
	}
	public String toString() {
		StringBuilder s = new StringBuilder("Etat du plateau :\n\n* * * * * * * * * * * * *\n");
		for(int i = 0; i<SPOT;++i) {
			s.append("*\t*\t*\t*\n*") ;
			for(int j = 0; j<SPOT;++j) {
				if (!this.estOccupée(i, j) ) {
					if(j+1==SPOT)
						s.append("   O   *");
					else
						s.append("\t*");
				}
				else {
					s.append("   "+this.getPièce(i, j).getCouleur()+"   *");
				}
			}
			s.append("\n*\t*\t*\t*\n* * * * * * * * * * * * *\n");
		}
		return s.toString();
	}
	
	public String toString(Pièce pi, int[][] m) {
		int cmp = 0;
		StringBuilder s = new StringBuilder("\n* * * * * * * * * * * * *\n");
		for(int i = 0; i<SPOT;++i) {
			s.append("*\t*\t*\t*\n*");
			for(int j = 0; j<SPOT;++j) {
				if (!this.estOccupée(i, j) || this.getPièce(i, j)==pi) {
					if(j+1==SPOT && m[i][j] == 0)
						s.append("   O   *");
					else if(m[i][j] == 0)
						s.append("\t*");
					else if(m[i][j] == 1)
						s.append("   "+ ++cmp +"   *");
					else if(m[i][j] == 2) 
						s.append(" "+ ++cmp +" - "+ ++cmp+" *");
				}
				else  {
					s.append("   "+this.getPièce(i, j).getCouleur()+"   *");
				}
			}
			s.append("\n*\t*\t*\t*\n* * * * * * * * * * * * *\n");
		}
		return s.toString();
	}
	public int getCmp(Pièce pi, int[][] m) {
		int cmp = 0;
		for(int i = 0; i<SPOT;++i) {
			for(int j = 0; j<SPOT;++j) {
				if (!this.estOccupée(i, j) || this.getPièce(i, j)==pi) {
					if(m[i][j] == 1)
						++cmp;
					else if(m[i][j] == 2) 
						cmp = cmp + 2;
				}
			}
		}
		return cmp;
	}
	public Pièce getPièce(int i, int j) {
		return this.p[i][j];
	}
	public int mouvPossible(Pièce pi, int i, int j) {
		int res = 0;
		if(i>0 && this.mouvHautPossible(pi, i, j))
			++res;
		if(j<2 && this.mouvDroitPossible(pi, i, j))
			++res;
		return res;
	}
	public int[][] calculMouvements(Pièce pi){
		int[][] movs = new int[SPOT][SPOT];
		for(int i=0; i<SPOT;++i) {
			for(int j=0;j<SPOT;++j) {
				movs[i][j] = this.mouvPossible(pi, i, j);
			}
		}
		return movs;
	}
	public boolean mouvHautPossible(Pièce pi, int i, int j) {
		assert(i>0);
		return ((!this.estOccupée(i, j) && (this.getPièce(i-1, j)==pi || !this.estOccupée(i-1, j))) ||
			(this.getPièce(i, j)==pi && !this.estOccupée(i-1, j)));
	}
	public boolean mouvDroitPossible(Pièce pi, int i, int j) {
		assert(j<2);
		return ((!this.estOccupée(i, j) && (this.getPièce(i, j+1)==pi || !this.estOccupée(i, j+1))) ||
			(this.getPièce(i, j)==pi && !this.estOccupée(i, j+1)));
	}
	public boolean estOccupée(int i, int j) {
		return this.getPièce(i, j) != null;
	}
	public void setPièce(Pièce pi, int i, int j) {
		this.p[i][j] = pi;
	}
	public int getPoints(int numJoueur) {
		return points[numJoueur];
	}
	public void incPoints(Pièce pi) {
		switch(pi.getCouleur()) {
		case R:
			++this.points[0];
			break;
		case B:
			++this.points[1];
			break;
		case W:
			break;
		default:
			break;
		}
		
	}
	public void jouer(Pièce pi, Scanner sc) {
		int choix = 0;
		System.out.println(this.toString());
		System.out.println("Points Joueur Rouge = "+ this.getPoints(0)+"; Points Joueur Bleu = "+ this.getPoints(1));
		int[][] m = this.calculMouvements(pi);
		int cmp = this.getCmp(pi, m);
		System.out.println(this.toString(pi, m)+"\n");
		do {
			switch(pi.getCouleur()) {
				case R:
					System.out.println("Le joueur de la pièce Rouge saisit une case de déplacement : ");
					while (!sc.hasNextInt()) sc.next();
					choix = sc.nextInt();
					break;
				case B:
					System.out.println("Le joueur de la pièce Bleue saisit une case de déplacement : ");
					while (!sc.hasNextInt()) sc.next();
					choix = sc.nextInt();
					break;
				case W:
					System.out.println("Le joueur qui vient de jouer saisit une case de déplacement pour la pièce blanche : ");
					while (!sc.hasNextInt()) sc.next();
					choix = sc.nextInt();
					break;
				default:
					break;
			}
		}while(0>=choix || choix>cmp);
		System.out.println("\nChoix Correct");
		this.poserPièce(pi, choix, m);
	}
	public void retirerPièce(Pièce pi) {
		for(int i = 0; i<SPOT;++i) {
			for(int j = 0; j<SPOT;++j) {
				if(this.getPièce(i, j) == pi)
					this.setPièce(null,i, j);
			}
		}
	}
	public void poserPièce(Pièce pi, int choix, int[][] m) {
		int res = 0;
		for(int i = 0; i<SPOT;++i) {		
			for(int j = 0; j<SPOT;++j) {
				if(i>0 && m[i][j]>0 && this.mouvHautPossible(pi, i, j)) {
					++res;
					if(res == choix) {
						this.retirerPièce(pi);
						this.setPièce(pi, i, j);
						this.setPièce(pi, i-1, j);
						if(j+1 == SPOT) {
							this.incPoints(pi);
							this.incPoints(pi);
						}
						
						return;
					}
				}
					
				if(j<2 && m[i][j]>0 && this.mouvDroitPossible(pi, i, j)) {
					++res;
					if(res == choix) {
						this.retirerPièce(pi);
						this.setPièce(pi, i, j);
						this.setPièce(pi, i, j+1);
						if(j-1 == SPOT-1) {
							this.incPoints(pi);
						}
						return;
					}
				}				
			}
		}
	}
	public void lancerJeu() {
//		System.out.println("-Vous venez de lancer une partie de 3 SPOT GAME-\n\n.Présentation des règles:\n1.Chaque joueur choisit la pièce colorée qu'il va jouer tout au long de la partie."
//				+ " La pièce blanche est neutre.\n2.Le jeu commence lorsqu'un joueur déplace sa pièce sur le plateau vers une nouvelle position. Au moins un nouveau carré doit être couvert "
//				+ "par la nouvelle position. Les déplacements seront représentés par des numéros. lorsque deux numéros sont affichés dans une même case");
		Scanner sc = new Scanner(System.in);
		while(true) {
			jouer(pièceRouge, sc);
			if(this.getPoints(0) >= SCORE_FIN_JEU)
				break;
			jouer(pièceBlanche, sc);
			jouer(pièceBleue, sc);
			if(this.getPoints(1) >= SCORE_FIN_JEU)
				break;
			jouer(pièceBlanche, sc);
		}
		if(this.getPoints(0) == SCORE_FIN_JEU) {
			if(this.getPoints(1) >= SCORE_CONDITION) 
				System.out.println("Le joueur Rouge a gagné");
			else
				System.out.println("Le joueur Bleu a gagné");
		}
		else if(this.getPoints(1) == SCORE_FIN_JEU) {
			if(this.getPoints(0) >= SCORE_CONDITION) 
				System.out.println("Le joueur Bleu a gagné");
			else
				System.out.println("Le joueur Rouge a gagné");
		}
		sc.close();
	}
}
