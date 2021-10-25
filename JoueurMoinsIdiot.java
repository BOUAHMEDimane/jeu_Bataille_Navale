package batailleNavale;

public class JoueurMoinsIdiot extends JoueurAvecGrille {
	
	public int nbCoupPrec;
	public Coordonnee [] coorCoupsPrec;
	
	
	public JoueurMoinsIdiot(GrilleNavale g, String nom) {
		super(g, nom);
		nbCoupPrec = 0;
		coorCoupsPrec = new Coordonnee [super.getTailleGrille() * super.getTailleGrille()];
	}
	
	public JoueurMoinsIdiot(GrilleNavale g) {
		super(g);
		nbCoupPrec = 0;
		coorCoupsPrec = new Coordonnee [super.getTailleGrille() * super.getTailleGrille()];
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		
		if (etat > 4)
	   		 throw new IllegalArgumentException("Etat inexistant : " + etat);
		
		//nbCoupPrec += 1;
		//coorCoupsPrec[nbCoupPrec] = c;
		
		
	   	 if (etat == 1) {
	   		 System.out.println("Vous avez touch� le navire en " + c);
	   	 } else if (etat == 2) {
	   		 System.out.println("Vous avez coul� le navire en " + c);
	   	 } else if (etat == 3) {
	   		 System.out.println("Tir en " + c + " dans l'eau.");
	   	 } else {
	   		 System.out.println("Vous avez gagn� !");
	   	 }
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		if (etat > 4)
	   		 throw new IllegalArgumentException("Etat inexistant : " + etat);

	   	 if (etat == 1) {
	   		 System.out.println("Votre navire en " + c + " est touch�.");
	   	 } else if (etat == 2) {
	   		 System.out.println("Votre navire en " + c + " est coul�.");
	   	 } else if (etat == 3) {
	   		 System.out.println("Tir en " + c + " dans l'eau.");
	   	 } else {
	   		 System.out.println("GAME OVER");
	   	 }
	}
	
	
	
	// le joueur moins idiot tire au hasard mais jamais deux fois dans la m�me case
	public Coordonnee choixAttaque() {
		
		Coordonnee choix;
		int i = 0;
		
		// joueur al�atoire
		if (nbCoupPrec == 0) {
			choix = new Coordonnee((int) (Math.random() * super.getTailleGrille()), (int) (Math.random() * super.getTailleGrille()));
		} else {
			choix = new Coordonnee((int) (Math.random() * super.getTailleGrille()), (int) (Math.random() * super.getTailleGrille()));
			while (i < nbCoupPrec && coorCoupsPrec[i] != choix) {
				i++;
			}
			if (i != nbCoupPrec) {
				return this.choixAttaque();
			} 	
		}
		nbCoupPrec += 1;
		coorCoupsPrec[nbCoupPrec] = choix;
		return choix;
		
	}

	

	public static void main(String[] args) {
		
		
		//JoueurMoinsIdiot j = new JoueurMoinsIdiot();
		
	}

}


