package batailleNavale;

public class GrilleNavale {
    // attributs
    private Navire[] navires; // navires placé sur la grille
    private int nbNavires; // n° navires sur la grille
    private int taille; // taille grille (carrées)
    private Coordonnee[] tirsRecus; // coordonnées tirs reçus
    private int nbTirsRecus; // n° tirs reçus

    // 1er constructeur

    public GrilleNavale(int taille, int[] taillesNavires) {
   	 // construit grille de taille taille où taillesNavires.length ont été placés au
   	 // hasard
   	 // Trouver le tableau taillesNavires avec sa taille maximale
   	 int max = taillesNavires[0];
   	 for (int i = 1; i < taillesNavires.length; i++) {
   		 if (taillesNavires[i] <= 0) {
   			 throw new IllegalArgumentException("Un navire ne peut pas avoir une taille négative ou nulle !");
   		 } else if (taillesNavires[i] > max)
   			 max = taillesNavires[i];
   	 }
   	 // 1miere condition (taille grille entre 1 a 25)
   	 if (taille > 26 || taille < 1)
   		 throw new IllegalArgumentException("La taille de la grille doit être comprise entre 1 et 26 !");
   	 else if (taille < max)
   		 throw new IllegalArgumentException(
   				 "La taille de grille doit être supérieure ou égale à la taille du plus grand des navires !");
   	 else
   		 this.taille = taille;

   	 nbNavires = 0;
   	 navires = new Navire[taillesNavires.length];
   	 nbTirsRecus = 0;
   	 tirsRecus = new Coordonnee[taille * taille];
   	 placementAuto(taillesNavires);
    }

    // 2ieme constructeur

    public GrilleNavale(int taille, int nbNavires) {
   	 // permet d'obtenir une grille navale vide de taille taille pouvant accueillir
   	 // jusqu'à nbNavires
   	 if (taille > 26)
   		 throw new IllegalArgumentException("La taille de la grille ne peut pas être supérieure à 26 !");
   	 if (taille <= 0)
   		 throw new IllegalArgumentException("La taille de la grille ne peut pas être inférieure ou égale à 0 !");
   	 this.taille = taille;
   	 if (nbNavires <= 0)
   		 throw new IllegalArgumentException("Il est necessaire d'avoir au moins un navire !");
   	 this.nbNavires = 0;
   	 nbTirsRecus = 0;
   	 navires = new Navire[nbNavires];
   	 tirsRecus = new Coordonnee[taille * taille];

    }

//1 String to String()

    public String toString() {
   	 StringBuilder sb = new StringBuilder();
   	 sb.append("  ");
   	 for (int i = 1; i < taille + 1; i++) {
   		 sb.append("  ");
   		 sb.append((char) ('A' + i - 1));
   	 }
   	 sb.append('\n');
   	 for (int i = 0; i < taille; i++) {
   		 if (i + 1 < 10)
   			 sb.append(" ");
   		 sb.append(i + 1);
   		 sb.append("  ");
   		 for (int j = 0; j < taille; j++) {
   			 Coordonnee c = new Coordonnee(i, j);
   			 boolean tir = false;
   			 for (int t = 0; t < tirsRecus.length; t++) {
   				 if (c == tirsRecus[t])
   					 tir = true;
   			 }
   			 boolean contient = false;
   			 for (int n = 0; n < navires.length; n++) {
   				 if (navires[n].contient(c))
   					 contient = true;
   			 }
   			 if (contient) {
   				 if (tir)
   					 sb.append('X');
   				 else
   					 sb.append('#');
   			 } else {
   				 if (tir)
   					 sb.append('O');
   				 else
   					 sb.append('.');
   			 }
   			 sb.append("  ");
   		 }
   		 sb.append('\n');
   	 }
   	 return sb.toString();
    }

//2 getTaille()

    public int getTaille() {
   	 return taille;
    }

//3 ajouteNavire()
    public boolean ajouteNavire(Navire n) {
   	 if (!estDansGrille(n.getFin()) || !estDansGrille(n.getDebut()))
   		 return false;
   	 for (int i = 0; i < nbNavires; i++) {
   		 if (navires[i].touche(n) || navires[i].chevauche(n)) {
   			 return false;
   		 }
   	 }
   	 navires[nbNavires] = n;
   	 nbNavires++;
   	 return true;
    }

//4.1 placementAuto()

    public Coordonnee creerCoordAlea(int longueur) {
   	 return new Coordonnee((int) (Math.random() * (this.taille - longueur)),
   			 (int) (Math.random() * (this.taille - longueur)));
    }

//4.2 placementAuto()

    public void placementAuto(int[] taillesNavires) {

   	 // étape1 : classer taillesNavires du plus grand au plus petit navire
   	 int a; // intermédiaire d'échange
   	 for (int i = 0; i < taillesNavires.length - 1; i++) {
   		 if (taillesNavires[i] < taillesNavires[i + 1]) {
   			 a = taillesNavires[i];
   			 taillesNavires[i] = taillesNavires[i + 1];
   			 taillesNavires[i + 1] = a;
   			 i = 0;
   		 }
   	 }
   	 // étape2 placement des navires
   	 for (int i = 0; i < taillesNavires.length; i++) {
   		 int compteur = 0;
   		 while (compteur < 50) {
   			 boolean randomPosition = (Math.random() < 0.5);
   			 Coordonnee randomC = creerCoordAlea(taillesNavires[i]);
   			 Navire n = new Navire(randomC, taillesNavires[i], randomPosition);
   			 if (this.ajouteNavire(n))
   				 break;
   			 compteur += 1;
   		 }
   		 if (compteur >= 50)
   			 throw new IllegalArgumentException("Il est impossible d'ajouter le navire " + i);
   	 }
    }

//5 estDansGrille()

//on utilise 2 methodes de classe coordonees, getLigne, getColonne  + getTaille (classe grilleNavale)
//+ 1 car 1-26

    private boolean estDansGrille(Coordonnee c) {
   	 return (c.getLigne() < this.taille && c.getColonne() < taille);
    }

//6 estDansTirsReçus()

    private boolean estDansTirsRecus(Coordonnee c) {
   	 for (int i = 0; i < nbTirsRecus; i++) {
   		 if (c.equals(tirsRecus[i]))
   			 return true;
   	 }
   	 return false;
    }

//7 ajouteDansTirsReçus()
    private boolean ajouteDansTirsRecus(Coordonnee c) {
   	 boolean ajBien = false;
   	 // si les coordonees sont places dans la grille ^ ¬ (estDansTirsReçus) alors
   	 if (estDansGrille(c) && !estDansTirsRecus(c)) {
   		 tirsRecus[nbTirsRecus] = c;
   		 nbTirsRecus += 1;
   		 return true;
   	 }
   	 return (ajBien);
    }

//8 reçoitTir

    public boolean recoitTir(Coordonnee c) {
   	 boolean bool = false;
   	 if (!estDansTirsRecus(c)) {
   		 this.ajouteDansTirsRecus(c);
   		 // le tir n'est pas dans tirsRecus[] et un des navires contient la coordonnee c
   		 for (int i = 0; i < nbNavires; i++)
   			 if (navires[i].recoitTir(c))// actualisation navire touchée
   				 bool = true;
   	 }
   	 return bool;
    }

//9 estTouche

    public boolean estTouche(Coordonnee c) {
   	 // return true ssi un des navires dans this a été touché en c
   	 boolean var = false;
   	 for (int i = 0; i < nbNavires; i++) {
   		 if (this.navires[i].contient(c) && estDansTirsRecus(c)) {
   			 var = true;
   		 }
   	 }
   	 return var;
    }

//10 estALEau()

    public boolean estALEau(Coordonnee c) {
   	 return (estDansTirsRecus(c) && !estTouche(c));
    }

//11 estCoule()

    public boolean estCoule(Coordonnee c) {
   	 boolean clr = false;
   	 for (int i = 0; i < this.navires.length; i++) {
   		 if (this.navires[i] != null && this.navires[i].estTouche(c) && this.navires[i].estCoule()) {
   			 clr = true;
   			 break;
   		 }
   	 }
   	 return clr;
    }

//12 perdu()    

    public boolean perdu() {
   	 boolean perdre = true;
   	 for (int i = 0; i < nbNavires; i++) {
   		 if (!navires[i].estCoule()) {
   			 perdre = false;
   		 }
   	 }
   	 return perdre;
    }

    public static void main(String[] args) {
   	 int[] tab = { 2, 6, 4, 5, 3 };
   	 GrilleNavale g = new GrilleNavale(20, tab);
   	 Coordonnee a = new Coordonnee(19, 19);
   	 Coordonnee b = new Coordonnee("B4");
   	 Navire test = new Navire(b, 5, true);
//   	 System.out.println(g.estDansGrille(b));
   	 g.tirsRecus[0] = a;
   	 g.nbTirsRecus += 1;
   	 for (int i = 0; i < g.nbTirsRecus; i++) {
   		 System.out.println(g.tirsRecus[i]);
   	 }
   	 System.out.println(g.recoitTir(b));
   	 System.out.println(g.estDansTirsRecus(b));
   	 for (int i = 0; i < g.nbTirsRecus; i++) {
   		 System.out.println(g.tirsRecus[i]);
   	 }
//   	 System.out.println(g.estTouche(b));
//   	 System.out.println(g.toString());
    }

}

