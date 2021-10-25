package batailleNavale;

public class JoueurAuto extends JoueurAvecGrille {
	
	int nbCoupPrec;
	Coordonnee [] coorCoupPrec;
	
	
	public JoueurAuto(GrilleNavale g, String nom) {
		super(g, nom);
		nbCoupPrec = 0;
		coorCoupPrec = new Coordonnee [super.getTailleGrille()*super.getTailleGrille()];
	}
	
	public JoueurAuto(GrilleNavale g) {
		super(g);
		nbCoupPrec = 0;
		coorCoupPrec = new Coordonnee [super.getTailleGrille()*super.getTailleGrille()];
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		
		if (etat > 4)
	   		 throw new IllegalArgumentException("Etat inexistant : " + etat);
		
		//resAttaque = etat;
		//coorCoupPrec = c;
		
	   	 if (etat == 1) {
	   		 System.out.println("Vous avez touché le navire en " + c);
	   	 } else if (etat == 2) {
	   		 System.out.println("Vous avez coulé le navire en " + c);
	   	 } else if (etat == 3) {
	   		 System.out.println("Tir en " + c + " dans l'eau.");
	   	 } else {
	   		 System.out.println("Vous avez gagné !");
	   	 }
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		if (etat > 4)
	   		 throw new IllegalArgumentException("Etat inexistant : " + etat);

	   	 if (etat == 1) {
	   		 System.out.println("Votre navire en " + c + " est touché.");
	   	 } else if (etat == 2) {
	   		 System.out.println("Votre navire en " + c + " est coulé.");
	   	 } else if (etat == 3) {
	   		 System.out.println("Tir en " + c + " dans l'eau.");
	   	 } else {
	   		 System.out.println("GAME OVER");
	   	 }
	}
	
	
	
	public Coordonnee choixAttaque() {
		
		Coordonnee choix;
		int i = 0;
		
		// joueur aléatoire
		if (nbCoupPrec == 0) {
			choix = new Coordonnee((int) (Math.random() * super.getTailleGrille()), (int) (Math.random() * super.getTailleGrille()));
		} else {
			choix = new Coordonnee((int) (Math.random() * super.getTailleGrille()), (int) (Math.random() * super.getTailleGrille()));
			while (i < nbCoupPrec && coorCoupPrec[i] != choix) {
				i++;
			}
			if (i != nbCoupPrec) {
				return this.choixAttaque();
			} 	
		}
		return choix;
		
	}
	


	public static void main(String[] args) {
		
		GrilleNavale grille = new GrilleNavale(10, 3);
		//Coordonnee c = new Coordonnee("D4");
		JoueurAuto j = new JoueurAuto(grille, "Joueur");
		//j.retourAttaque(c, 5);
		System.out.println(j.choixAttaque());
		
	}

}


