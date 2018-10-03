package threeSpotGame;

public class Plateau {
	public final static int TAILLE = 3;
	private Case[][] p;

	public Plateau(int Taille) {
		p = new Case[TAILLE][TAILLE];
		for(int i=0; i<Taille;++i) {
			for (int j=0; j<Taille;++j) {
				p[i][j] = new Case();
				if(j==Taille-1)
					p[i][j].setSpot();
			}
		}
	}
	public void init(Pièce p1, Pièce pn, Pièce p2) {
		for(int i=0; i<TAILLE;++i) {
			for (int j=0; j<TAILLE;++j) {
				p[i][j] = new Case();
				if(j!=0) {
					switch(i) {
					case(0):
						this.p[i][j].setPièce(p1);
						break;
					case(1):
						this.p[i][j].setPièce(pn);
						break;
					case(2):
						this.p[i][j].setPièce(p2);
						break;
					}
				}
			}
		}
	}
	public String firstToString(){
		String s = "* * * * * * * * * * * * *\n";
		for(Case[] i : this.p) { //i = ligne
			s += "*\t*\t*\t*\n";
			for(Case j : i) { // j = colonne
				s+="*";
				if (!j.estOccupée() && !j.estSpot()){
					s+= "\t";
				}
				else if(!j.estOccupée() && j.estSpot()){
					s+= "   O   ";	
				}
				else if(j.estOccupée()){
					s+= "   "+j.getPièce().getCouleurPièce()+"   ";
				}
				s +="*\n*\t*\t*\t*\n";
			}
			s += "* * * * * * * * * * * * *";
		}
		return s;
	}
	public String secondToString(){
		int cmp = 0;
		String s = "* * * * * * * * * * * * *\n";
		for(Case[] i : this.p) { //i = ligne
			s += "*\t*\t*\t*\n";
			for(Case j : i) { // j = colonne
				s+="*";
				if (j.getMov()==Case.mov.O){
					s+= "\t";
				}
				else if(!j.estOccupée() && j.estSpot()){
					s+= "   O   ";	
				}
				else if(j.estOccupée()){
					s+= "   "+j.getPièce().getCouleurPièce()+"   ";
				}
				s +="*\n*\t*\t*\t*\n";
			}
			s += "* * * * * * * * * * * * *";
		}
		return s;
	}
	public Case getCase(int i, int j) {
		return this.p[i][j];
	}
	public boolean estOccupée(int i, int j) {
		return this.getCase(i,j).estOccupée();
	}
	public boolean CaseHauteOccupée(int i, int j) {
		if (i>0)
			return this.getCase(i-1,j).estOccupée();
		return true;
	}
	public boolean CaseDroiteOccupée(int i, int j) {
		if (j<2)
			return this.getCase(i,j+1).estOccupée();
		return true;
	}
	public Case getCaseDroite(int i, int j) {
		if (j<2)
			return this.getCase(i,j+1);
		return null;
	}
	public Case getCaseHaute(int i, int j) {
		if (i>0)
			return this.getCase(i-1,j);
		return null;
	}
	
	public void movsDispos(Pièce p) { 
		for(int i=0; i<TAILLE;++i) {
			for (int j=0; j<TAILLE;++j) {
				if(!this.getCase(i, j).estOccupée() ||
				this.getCase(i, j).getPièce().getCouleurPièce()==p.getCouleurPièce()){
					if(!this.CaseDroiteOccupée(i, j) && !this.CaseHauteOccupée(i, j)) {
						this.getCase(i, j).setDéplacement(this.getCaseHaute(i, j), this.getCaseDroite(i, j));
						this.getCase(i, j).setMov(Case.mov.HV);
					}
					else if(!this.getCase(i, j).estOccupée() && !this.CaseHauteOccupée(i, j)&& this.CaseDroiteOccupée(i, j)) {
						this.getCase(i, j).setDéplacement(this.getCaseHaute(i, j), null);
						this.getCase(i, j).setMov(Case.mov.V);
					}
					else if(!this.getCase(i, j).estOccupée() && !this.CaseDroiteOccupée(i, j) && this.CaseHauteOccupée(i, j) ) {
						this.getCase(i, j).setDéplacement(null, this.getCaseDroite(i, j));
						this.getCase(i, j).setMov(Case.mov.H);
					}
					else
						this.getCase(i, j).setMov(Case.mov.O);
				}	
			}
		}
	}
	public void effectuerDéplacement(Pièce p, int selec) {
		int cmp_selec=0;
		for(int i=0; i<TAILLE;++i) {
			for (int j=0; j<TAILLE;++j) {
				if(!this.getCase(i, j).estOccupée() ||
					this.getCase(i, j).getPièce().getCouleurPièce()==p.getCouleurPièce()) {
					if(this.getCase(i, j).getMov()==Case.mov.O)
						continue;
					else if(this.getCase(i, j).getMov()==Case.mov.V) {
						++cmp_selec;
						if(cmp_selec==selec) {
							this.getCase(i, j).libérer();
							this.getCaseHaute(i, j).libérer();
							p.déplacerPièce(this, this.getCase(i, j), Case.mov.V);
							return;
						}
					}
					else if(this.getCase(i, j).getMov()==Case.mov.H ) {
						++cmp_selec;
						if(cmp_selec==selec) {
							this.getCase(i, j).libérer();
							this.getCaseDroite(i, j).libérer();
							p.déplacerPièce(this, this.getCase(i, j), Case.mov.H);
							return;
						}
					}
					else if(this.getCase(i, j).getMov()==Case.mov.HV) {
						++cmp_selec;
						if(cmp_selec== selec) {
							this.getCase(i, j).libérer();
							this.getCaseHaute(i, j).libérer();
							p.déplacerPièce(this, this.getCase(i, j), Case.mov.V);
							return;
						}						
						++cmp_selec;
						if(cmp_selec== selec) {
							this.getCase(i, j).libérer();
							this.getCaseDroite(i, j).libérer();
							p.déplacerPièce(this, this.getCase(i, j), Case.mov.H);
							return;
						}
						continue;
					}
					
				}
			}	
		}
	}
}
