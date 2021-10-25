package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {
    private Scanner sc;

    public JoueurTexte(GrilleNavale g, String nom) {
   	 super(g, nom);
    }

    public JoueurTexte(GrilleNavale g) {
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

    public Coordonnee choixAttaque() {
   	 sc = new Scanner(System.in);
   	 System.out.println("Donner la coordonnee ou vous attaquez : ");
   	 String cClavier = sc.next();
   	 Coordonnee c = new Coordonnee(cClavier);
   	 return c;
    }

    public static void main(String[] args) {
   	 int[] tab = { 2, 3, 5 };
   	 GrilleNavale g = new GrilleNavale(26, tab);
   	 JoueurTexte a = new JoueurTexte(g, "JoueurA");
   	 JoueurTexte b = new JoueurTexte(g, "JoueurB");
   	 System.out.println(a.getNom());
   	 System.out.println(b.getNom());
   	 a.jouerAvec(b);
    }

}



