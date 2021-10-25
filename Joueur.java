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
   		 throw new IllegalArgumentException("La Grille ne peut pas etre de taille superieur � 25");
   	 this.nom = nom;
   	 this.tailleGrille = tailleGrille;
    }

    public Joueur(int tailleGrille) {
   	 this(tailleGrille, "Joueur");
    }

    // M�thodes
    public int getTailleGrille() {
   	 return this.tailleGrille;
    }

    public String getNom() {
   	 return this.nom;
    }

    public void jouerAvec(Joueur j) {
   	 if (this.tailleGrille != j.tailleGrille)
   		 throw new IllegalArgumentException("tailles de grilles diff�rentes");
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
   		 Joueur x = attaquant; // on inverse les r�les
   		 attaquant = defenseur;
   		 defenseur = x;
   	 }
    }

    protected abstract void retourAttaque(Coordonnee c, int etat);
    // Cette m�thode est invoqu�e sur le joueur attaquant � la fin d�un tour de jeu.
    // c'est la coordonn�e � laquelle le tir a eu lieu et etat le r�sultat de
    // l'attaque. etat ne peut �tre que TOUCHE, COULE, A_L_EAU, ou GAMEOVER

    protected abstract void retourDefense(Coordonnee c, int etat);
    // Cette m�thode est invoqu�e sur le joueur d�fenseur � la fin d�un tour de jeu.
    // cest la coordonn�e � laquelle le tir a eu lieu et etat le r�sultat de ce tir.
    // etat ne peut �tre que TOUCHE, COULE, A_L_EAU, ou GAMEOVER.

    public abstract Coordonnee choixAttaque();
    // Cette m�thode est invoqu�e sur le joueur attaquant au d�but d�un tour de
    // jeu.Elle retourne la coordonn�e cible du tir effectu� par l�attaquant.

    public abstract int defendre(Coordonnee c);
    // Cette m�thode est invoqu�e surle joueur d�fenseur apr�s le choix de
    // l�attaquant, cest la coordonn�e � laquelle l�attaquant a choisi d�effectuer
    // un tir. Elle retourne le r�sultat du tir qui ne peut �tre que TOUCHE, COULE,
    // A_L_EAU, ou GAMEOVER.

    public static void main(String[] args) {
   	 // TODO Auto-generated method stub

    }

}


