package batailleNavale;

import java.awt.*;

public class GrilleNavaleGraphique extends GrilleNavale {
    private GrilleGraphique grille;

    public GrilleNavaleGraphique(int taille, int nbNavires) {

   	 super(taille, nbNavires);

   	 grille = new GrilleGraphique(taille);
   	 grille.setClicActive(false);;

   	 // this.setLayout(new GridLayout(taille + 1, taille + 1));
    }

    // Accesseur en lecture pour grille.
    public GrilleGraphique getGrilleGraphique() {

   	 return this.grille;

    }

    /*
     * Spécialisation de la méthode héritée de GrilleNavale. Les cases correspondant
     * au navire ajouté doivent être coloriées en Color.GREEN.
     */

    public boolean ajouteNavire(Navire n) {

   	 boolean res = super.ajouteNavire(n);
   	 if (res /*&& grille!=null*/) {

   		 this.grille.colorie(n.getDebut(), n.getFin(), Color.GREEN);

   		 // avec grille, on doit faire l'affichage du navire
   	 }
   	 return res;

   	 // cases[cord.getLigne()][cord.getColonne()].setBackground(color);

   	 // jPanel.setBackground(Color.GREEN)
    }

    /*
     * Spécialisation de la méthode héritée de GrilleNavale. La case correspondant
     * au tir doit être coloriée en Color. RED si le tir a touché un navire ou en
     * Color.BLUE s'il est à l'eau.
     */

    public boolean recoitTir(Coordonnee c) {

   	 boolean res = super.recoitTir(c);
   	 if (res || this.estTouche(c)) {

   		 this.grille.colorie(c, Color.RED);
   		 return true;

   	 }

   	 else {
   		 this.grille.colorie(c, Color.BLUE);
   		 return false;
   	 }

    }

}

