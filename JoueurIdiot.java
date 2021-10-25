package batailleNavale;

public class JoueurIdiot extends JoueurAvecGrille {
	
	
	public JoueurIdiot(GrilleNavale g, String nom) {
		super(g, nom);

	}
	
	public JoueurIdiot(GrilleNavale g) {
		super(g);
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		
		if (etat > 4)
	   		 throw new IllegalArgumentException("Etat inexistant : " + etat);
		
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
	
	
	// le joueur idiot tire au hasard
	public Coordonnee choixAttaque() {
		
		return new Coordonnee((int) (Math.random() * super.getTailleGrille()), (int) (Math.random() * super.getTailleGrille()));
	}

	

	public static void main(String[] args) {
		
		
	}

}


