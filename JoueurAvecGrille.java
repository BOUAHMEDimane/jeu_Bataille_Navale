package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {

    private GrilleNavale grille;

    public JoueurAvecGrille(GrilleNavale g, String nom) {
   	 super(g.getTaille(), nom);
   	 this.grille = g;

    }

    public JoueurAvecGrille(GrilleNavale g) {
   	 super(g.getTaille());
    }

    @Override
    public int defendre(Coordonnee c) {

   	 int resultTir = 0;

   	 if (grille.recoitTir(c)) {
   		 if (grille.perdu()) {
   			 resultTir = GAMEOVER;
   		 } else if (grille.estCoule(c)) {
   			 resultTir = COULE;
   		 } else if (grille.estTouche(c)) {
   			 resultTir = TOUCHE;
   		 } else if (grille.estALEau(c)) {
   			 resultTir = A_L_EAU;
   		 }
   	 } else {
   		 resultTir = A_L_EAU;
   	 }
   	 return resultTir;
    }

    public static void main(String[] args) {
   	 // TODO Auto-generated method stub

    }

}




