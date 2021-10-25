package batailleNavale;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JOptionPane;


public class JoueurGraphique extends JoueurAvecGrille {

    private GrilleGraphique grilleTirs;

    /*
     * permet d'obtenir un joueur graphique de nom nom qui effectue des tirs en
     * cliquant sur grilleTirs et dont la flotte est plac�e sur grilleDefense.
     */
    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {

   	 super(grilleDefense, nom);
   	 this.grilleTirs = grilleTirs;

    }

    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {

   	 super(grilleDefense);
   	 this.grilleTirs = grilleTirs;

    }

    // Consiste � r�cup�rer la coordonn�e choisie depuis grilleTirs.
    // private Coordonnee coordonneeSelectionnee;
    /*@Override
    public Coordonnee choisirAttaque() {
   	 return this.grilleTirs.getCoordonneeSelectionnee();

    }*/
    
    

    protected void retourDefense(Coordonnee c, int etat) {

   	 // Affichage d'un JOptionPane lorsque le tir a touch� ou coul� un navire, ou
   	 // lorsque la partie est perdue.
   	 // Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
   	 
   	 switch (etat) {

   	 case TOUCHE:
   		 JOptionPane.showMessageDialog(grilleTirs, "Votre opposant a touch� un navire en " + c);
   		 break;

   	 case COULE:
   		 JOptionPane.showMessageDialog(grilleTirs, "Votre opposant a fait coul� un navire en " + c);
   		 break;

   	 case GAMEOVER:
   		 JOptionPane.showMessageDialog(grilleTirs, "Votre opposant a gagn�!!!");

   	 }

    }



    protected void retourAttaque(Coordonnee c, int etat) {
   	 Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
   	 grilleTirs.colorie(c, couleur);
   	 switch (etat) {
   	 case TOUCHE:
   		 JOptionPane.showMessageDialog(grilleTirs, "Vous avez touch� un navire en " + c);
   		 break;

   	 case COULE:
   		 JOptionPane.showMessageDialog(grilleTirs, "Vous avez coul� un navire en " + c);
   		 break;

   	 case GAMEOVER:
   		 JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagn�!!!");

   	 }

    }

    @Override
    public Coordonnee choixAttaque() {
   	 Coordonnee res =  grilleTirs.getCoordonneeSelectionnee();
   	 System.out.println(res);
   	 return res;
    }

}

