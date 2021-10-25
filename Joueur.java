package batailleNavale;

public abstract class Joueur {

    // Attributs
    public final static int TOUCHE = 1;
    public final static int COULE = 2;
    public final static int A_L_EAU = 3;
    public final static int GAMEOVER = 4;

    private Joueur adversaire;
    
    private int tailleGrille;
    private String nom;

    // Constructeurs

    public Joueur(int tailleGrille, String nom) {
   	 if (nom == null)
   		 throw new IllegalArgumentException("Le nom du joueur ne peut pas etre null");
   	 if (tailleGrille < 1)
   		 throw new IllegalArgumentException("La Grille ne peut pas etre de taille negatif");
   	 if (tailleGrille > 26)
   		 throw new IllegalArgumentException("La Grille ne peut pas etre de taille superieur à 25");
   	 this.nom = nom;
   	 this.tailleGrille = tailleGrille;
    }

    public Joueur(int tailleGrille) {
   	 this(tailleGrille, "Joueur");
    }

    // Méthodes
    public int getTailleGrille() {
   	 return this.tailleGrille;
    }

    public String getNom() {
   	 return this.nom;
    }

    public void jouerAvec(Joueur j) {
   	 if (this.tailleGrille != j.tailleGrille)
   		 throw new IllegalArgumentException("tailles de grilles différentes");
   	 j.adversaire = this;
   	 this.adversaire = j;
   	 System.out.println("coucou debut jeu");
   	 deroulementJeu(this, j);
    }

    private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
   	 int res = 0;
   	 while (res != GAMEOVER) {
   		 Coordonnee c = attaquant.choixAttaque();
   		 res = defenseur.defendre(c);
   		 attaquant.retourAttaque(c, res);
   		 defenseur.retourDefense(c, res);
   		 Joueur x = attaquant; // on inverse les rôles
   		 attaquant = defenseur;
   		 defenseur = x;
   	 }
    }

    protected abstract void retourAttaque(Coordonnee c, int etat);
    // Cette méthode est invoquée sur le joueur attaquant à la fin d’un tour de jeu.
    // c'est la coordonnée à laquelle le tir a eu lieu et etat le résultat de
    // l'attaque. etat ne peut être que TOUCHE, COULE, A_L_EAU, ou GAMEOVER

    protected abstract void retourDefense(Coordonnee c, int etat);
    // Cette méthode est invoquée sur le joueur défenseur à la fin d’un tour de jeu.
    // cest la coordonnée à laquelle le tir a eu lieu et etat le résultat de ce tir.
    // etat ne peut être que TOUCHE, COULE, A_L_EAU, ou GAMEOVER.

    public abstract Coordonnee choixAttaque();
    // Cette méthode est invoquée sur le joueur attaquant au début d’un tour de
    // jeu.Elle retourne la coordonnée cible du tir effectué par l’attaquant.

    public abstract int defendre(Coordonnee c);
    // Cette méthode est invoquée surle joueur défenseur après le choix de
    // l’attaquant, cest la coordonnée à laquelle l’attaquant a choisi d’effectuer
    // un tir. Elle retourne le résultat du tir qui ne peut être que TOUCHE, COULE,
    // A_L_EAU, ou GAMEOVER.

    public static void main(String[] args) {
   	 // TODO Auto-generated method stub

    }

}


