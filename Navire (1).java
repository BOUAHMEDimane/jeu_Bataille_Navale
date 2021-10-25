package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	public Navire(Coordonnee d, int longueur, boolean estVertical) {
		debut = d;
		nbTouchees = 0;
		partiesTouchees = new Coordonnee[longueur];
		if(estVertical == true) {
			if(d.getLigne() + longueur - 1 > 26)
				throw new IllegalArgumentException("Lignes doivent etre entre 0 et 25");
			fin = new Coordonnee(d.getLigne() + longueur -1 ,d.getColonne());
		}
		else {
			if(d.getColonne() + longueur - 1 > 26)
				throw new IllegalArgumentException("Colonnes doivent etre entre 0 et 25");
			fin = new Coordonnee(d.getLigne(),d.getColonne() + longueur -1);
		}
	}
	
	public String toString() {
		int longueur = 0;
		if(debut.getLigne() == fin.getLigne()) {
			longueur = fin.getColonne() - debut.getColonne() + 1;
			return "Navire(" + debut + ", " + longueur + ", horizontal)" ;
		}
		else {
			longueur = fin.getLigne() - debut.getLigne() + 1;
			return "Navire(" + debut + ", " + longueur + ", vertical)" ;
		}
	}
	
	public Coordonnee getDebut() {
		return debut;
	}
	
	public Coordonnee getFin() {
		return fin;
	}
	
	public boolean estVertical() {
		if(debut.getLigne() == fin.getLigne()) {
			return false ;
		}
		return true ;
	}
	
	public boolean contient(Coordonnee c) {
		if (estVertical() == true) {
			if(c.getColonne() != debut.getColonne())
				return false;
			for(int i = debut.getLigne(); i <= fin.getLigne(); i++) {
				if(c.getLigne() == i)
					return true;
			}
		}
		else {
			if(c.getLigne() != debut.getLigne())
				return false;
			for(int i = debut.getColonne(); i <= fin.getColonne(); i++) {
				if(c.getColonne() == i)
					return true;
			}
		}
		return false;
	}

	
	public boolean touche(Navire n) {
		return ((((Math.abs(this.fin.getLigne() -  n.debut.getLigne()) == 1) || (Math.abs(this.debut.getLigne() - n.fin.getLigne()) == 1)))
				&& (((n.debut.getColonne() >= this.debut.getColonne() && n.debut.getColonne() <= this.fin.getColonne())
						|| (n.fin.getColonne() >= this.debut.getColonne() && n.fin.getColonne() <= this.fin.getColonne()))
					|| ((this.debut.getColonne() >= n.debut.getColonne() && this.debut.getColonne() <= n.fin.getColonne())
						|| (this.fin.getColonne() >= n.debut.getColonne() && this.fin.getColonne() <= n.fin.getColonne())))
				|| 
				( ((Math.abs(this.fin.getColonne()-n.debut.getColonne()) == 1) || (Math.abs(this.debut.getColonne() -  n.fin.getColonne()) == 1) )
				&& (((n.debut.getLigne() >= this.debut.getLigne() && n.debut.getLigne() <= this.fin.getLigne())
						|| (n.fin.getLigne() >= this.debut.getLigne() && n.fin.getLigne() <= this.fin.getLigne()))
				|| ((this.debut.getLigne() >= n.debut.getLigne() && this.debut.getLigne() <= n.fin.getLigne())
						|| (this.fin.getLigne() >= n.debut.getLigne() && this.fin.getLigne() <= n.fin.getLigne()))))
				);
	}
	
	public boolean chevauche(Navire n) {
		if (n.estVertical() == true) {
			for(int i = n.debut.getLigne(); i < n.fin.getLigne()+1 ; i++ ) {
				if(contient(new Coordonnee(i,n.getDebut().getColonne())))
					return true;
			}
		}
		else {
			for(int i = n.debut.getColonne(); i < n.fin.getColonne()+1 ; i++ ) {
				if(contient(new Coordonnee(n.getDebut().getLigne(),i)))
					return true;
			}
		}
		return false;
	}
	
	//recoitTir
    public boolean recoitTir(Coordonnee c) {
    	 if(contient(c)) {
    		 boolean dejaTouche = false;
             for(int i = 0; i < nbTouchees; i++) {
            	 if(partiesTouchees[i].equals(c)){
            		 dejaTouche = true;
            	}
            }
            if(!dejaTouche) {
            	partiesTouchees[nbTouchees] = c;
            	nbTouchees = nbTouchees + 1;
            }
            return true;
         }
         return false;
     }
	
	public boolean estTouche(Coordonnee c) {
	// return true ssi this a été touché par un tir en c
		for(int i = 0; i < nbTouchees ; i++) {
			if(partiesTouchees[i] != null) {
       		 	if(partiesTouchees[i].equals(c))
       		 		return true;
			}
		}
		return false;
	}
	
	public boolean estTouche() {
	   	 // return true ssi this a au moins une partie touchée
	   	 Boolean bool = false;
	   	 if (this.nbTouchees != 0) {
	   		 bool = true;
	   	 }
	   	 return bool;
	    }
	
	 public boolean estCoule() {
	   	 return (this.nbTouchees == partiesTouchees.length);
	 }

	public static void main(String[] args) {
		System.out.println("Test n.touche() sur bateau vertical -->");
		Navire n1 = new Navire(new Coordonnee("C3"), 4, true);
		if(!n1.touche(new Navire(new Coordonnee("D3"), 4, true)))
			System.out.println("Ca touche en D3");
		if(!n1.touche(new Navire(new Coordonnee("D4"), 4, true)))
			System.out.println("Ca touche en D4");
		if(!n1.touche(new Navire(new Coordonnee("D5"), 4, true)))
			System.out.println("Ca touche en D5");
		if(!n1.touche(new Navire(new Coordonnee("D6"), 4, true)))
			System.out.println("Ca touche en D6");
		//test fin de bateau sur cote
		if(!n1.touche(new Navire(new Coordonnee("A3"), 2, false)))
			System.out.println("Ca touche en B3");
		if(!n1.touche(new Navire(new Coordonnee("B4"), 2, true)))
			System.out.println("Ca touche en B4");
		if(!n1.touche(new Navire(new Coordonnee("A5"), 2, false)))
			System.out.println("Ca touche en B5");
		//test bateau en haut ou en bas
		if(!n1.touche(new Navire(new Coordonnee("C1"), 2, true)))
			System.out.println("1 : Ca touche en C2");
		if(!n1.touche(new Navire(new Coordonnee("C7"), 2, true)))
			System.out.println("1 : Ca touche en C7");
		if(!n1.touche(new Navire(new Coordonnee("A2"), 5, false)))
			System.out.println("2 : Ca touche en C2");
		if(!n1.touche(new Navire(new Coordonnee("B7"), 4, false)))
			System.out.println("2 : Ca touche en C7");
		//test bateau qui touche pas
		if(n1.touche(new Navire(new Coordonnee("E4"), 4, true)))
			System.out.println("1 : Ca touche pas");
		if(n1.touche(new Navire(new Coordonnee("F5"), 3, false)))
			System.out.println("2 : Ca touche pas");
		if(n1.touche(new Navire(new Coordonnee("A2"), 2, false)))
			System.out.println("3 : Ca touche pas");
		if(n1.touche(new Navire(new Coordonnee("D1"), 2, true)))
			System.out.println("4 : Ca touche pas");
		if(n1.touche(new Navire(new Coordonnee("D7"), 2, false)))
			System.out.println("5 : Ca touche pas");
		if(n1.touche(new Navire(new Coordonnee("B7"), 2, true)))
			System.out.println("6 : Ca touche pas");
		if(n1.touche(new Navire(new Coordonnee("A1"), 5, true)))
			System.out.println("7 : Ca touche pas");
		System.out.println("Fin test");
		
		System.out.println("Test n.touche() sur bateau horizontal -->");
		Navire n2 = new Navire(new Coordonnee("C3"), 4, false);
		//test en haut
		if(!n2.touche(new Navire(new Coordonnee("C2"), 4, false)))
			System.out.println("Ca touche en C2");
		if(!n2.touche(new Navire(new Coordonnee("A2"), 6, false)))
			System.out.println("Ca touche en C2 ");
		if(!n2.touche(new Navire(new Coordonnee("D1"), 2, true)))
			System.out.println("Ca touche en D2");
		if(!n2.touche(new Navire(new Coordonnee("F1"), 2, true)))
			System.out.println("Ca touche en F2");
		//test en bas
		if(!n2.touche(new Navire(new Coordonnee("A4"), 6, false)))
			System.out.println("Ca touche en C4");
		if(!n2.touche(new Navire(new Coordonnee("F4"), 2, false)))
			System.out.println("Ca touche en F4");
		if(!n2.touche(new Navire(new Coordonnee("D4"), 3, true)))
			System.out.println("Ca touche en D4");
		//test cotés
		if(!n2.touche(new Navire(new Coordonnee("B2"), 4, true)))
			System.out.println("Ca touche en B3");
		if(!n2.touche(new Navire(new Coordonnee("G2"), 6, true)))
			System.out.println("Ca touche en G3");
		if(!n2.touche(new Navire(new Coordonnee("A3"), 2, false)))
			System.out.println("Ca touche en B3");
		if(!n2.touche(new Navire(new Coordonnee("G3"), 4, false)))
			System.out.println("Ca touche en G3");
		//test bateau qui touche pas
		if(n2.touche(new Navire(new Coordonnee("D5"), 4, true)))
			System.out.println("1 : Ca touche pas");
		if(n2.touche(new Navire(new Coordonnee("B1"), 5, false)))
			System.out.println("2 : Ca touche pas");
		if(n2.touche(new Navire(new Coordonnee("H3"), 2, false)))
			System.out.println("3 : Ca touche pas");
		if(n2.touche(new Navire(new Coordonnee("A2"), 4, true)))
			System.out.println("4 : Ca touche pas");
		if(n2.touche(new Navire(new Coordonnee("D6"), 2, false)))
			System.out.println("5 : Ca touche pas");
		if(n2.touche(new Navire(new Coordonnee("D5"), 2, true)))
			System.out.println("6 : Ca touche pas");
		if(n2.touche(new Navire(new Coordonnee("F1"), 1, true)))
			System.out.println("7 : Ca touche pas");
		System.out.println("Fin test");

	}
}

