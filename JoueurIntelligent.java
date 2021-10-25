package batailleNavale;

public class JoueurIntelligent extends JoueurAvecGrille {

	public int nbCoupPrec;
	Coordonnee[] coorCoupsPrec;
	public int essai;
	public int resAttaque;
	public int direction;
	public int[] directionEssai;
	public int nbDirectionEssai;

	public JoueurIntelligent(GrilleNavale g, String nom) {
		super(g, nom);
		nbCoupPrec = 0;
		coorCoupsPrec = new Coordonnee[super.getTailleGrille() * super.getTailleGrille()];
		essai = 1;
		resAttaque = 0;
		direction = ' ';
		directionEssai = new int[4];
		nbDirectionEssai = 0;
	}

	public JoueurIntelligent(GrilleNavale g) {
		super(g);
		nbCoupPrec = 0;
		coorCoupsPrec = new Coordonnee[super.getTailleGrille() * super.getTailleGrille()];
		essai = 1;
		resAttaque = 0;
		direction = ' ';
		directionEssai = new int[4];
		nbDirectionEssai = 0;
	}

	protected void retourAttaque(Coordonnee c, int etat) {

		if (etat > 4)
			throw new IllegalArgumentException("Etat inexistant : " + etat);

		nbCoupPrec += 1;
		essai += 1;
		resAttaque = etat;
		coorCoupsPrec[nbCoupPrec] = c;

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

	// renvoie un int entre 1 et 4 qui n'est pas déjà dans le tableau directionEssai
	public int directionPasTestee() {

		// si la fonction est appelée mais que toutes les directions ont déjà été
		// essayées
		// TODO lever une exception (il y a peut-être un problème dans la gestion des
		// états après attaque)

		int randDir = (int) (Math.random() * 5 + 1);
		for (int i = 0; i < nbDirectionEssai; i++) {
			if (randDir == directionEssai[i]) {
				return this.directionPasTestee();
			}
		}

		return randDir;
	}

	// fonction qui renvoie la case voisine du coup précédent dans la direction
	// indiquée
	public Coordonnee voisineDirection() {

		Coordonnee choix;

		// haut
		if (direction == 1) {
			choix = new Coordonnee(coorCoupsPrec[nbCoupPrec].getLigne() - 1, coorCoupsPrec[nbCoupPrec].getColonne());
		} else if (direction == 2) {
			// bas
			choix = new Coordonnee(coorCoupsPrec[nbCoupPrec].getLigne() + 1, coorCoupsPrec[nbCoupPrec].getColonne());
		} else if (direction == 3) {
			// gauche
			choix = new Coordonnee(coorCoupsPrec[nbCoupPrec].getLigne(), coorCoupsPrec[nbCoupPrec].getColonne() - 1);
		} else {
			// droite
			choix = new Coordonnee(coorCoupsPrec[nbCoupPrec].getLigne(), coorCoupsPrec[nbCoupPrec].getColonne() + 1);
		}

		if (choix.getLigne() >= 0 || choix.getColonne() >= 0) {
			return choix;

			// case sort de la grille
		} else {

			// ajouter la direction qu'on ne peut pas prendre au tableau directionEssai
			directionEssai[nbDirectionEssai] = direction;
			nbDirectionEssai += 1;
			// chercher une nouvelle direction
			direction = this.directionPasTestee();
			// reprendre les coordonnées
			return this.voisineDirection();

		}

	}

	public Coordonnee choixAttaque() {

		// premier tour d'essai
		if (essai == 1) {
			// tirer dans une case au hasard
			return new Coordonnee((int) (Math.random() * super.getTailleGrille()),
					(int) (Math.random() * super.getTailleGrille()));

			// deuxième tour d'essai
		} else if (essai == 2) {

			// si le tir précédent était dans l'eau, tirer de nouveau au hasard
			if (resAttaque == A_L_EAU || resAttaque == COULE) {
				return new Coordonnee((int) (Math.random() * super.getTailleGrille()),
						(int) (Math.random() * super.getTailleGrille()));

				// sinon choisir une direction au hasard
			} else {
				direction = (int) (Math.random() * 5 + 1);
				// garder la direction en mémoire
				directionEssai[nbDirectionEssai] = direction;
				// incrémenter le nombre de directions essayées
				nbDirectionEssai += 1;
				// tirer dans cette direction
				return this.voisineDirection();
			}

			// tours d'essais suivants après deux essais
		} else {

			// si au tour précédent on a touché, continuer à tirer dans la même direction
			if (resAttaque == TOUCHE) {
				return this.voisineDirection();

				// si au tour précédent on est dans l'eau, choisir une autre direction
			} else {
				direction = directionPasTestee();
				// si au tour précédent on a coulé, essayer de trouver un autre navire à couler
				nbDirectionEssai = 0;
				directionEssai = new int[] { 0, 0, 0, 0 };
				essai = 1;
				return this.choixAttaque();
			}

		}

	}

	public static void main(String[] args) {

	}

}



