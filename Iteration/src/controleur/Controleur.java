package controleur;

import modele.*;
import vue.Ihm;

public class Controleur {
    private Ihm ihm;

    public Controleur(Ihm ihm){
        this.ihm = ihm;
    }

    public void lancerJeu() {
        // 1. Demander les noms et créer les joueurs
        String nomJ1 = ihm.demanderLeNom(1);
        String nomJ2 = ihm.demanderLeNom(2);
        Joueur joueur1 = new Joueur(nomJ1);
        Joueur joueur2 = new Joueur(nomJ2);

        // 2. Demander le choix du jeu
        int choix = ihm.demanderChoixJeu();

        // 3. Boucle de parties
        boolean rejouer = true;
        while (rejouer) {
            Partie partie;
            if (choix == 1) {
                partie = new Morpion(joueur1, joueur2);
            } else {
                partie = new Puissance4(joueur1, joueur2);
            }

            // Boucle d'une partie
            while (!partie.estTerminee()) {
                ihm.afficherGrille(partie.getGrille());
                Coup coup;
                if (choix == 1) {
                    coup = ihm.demanderCoupMorpion(partie.getJoueurCourant().getNom());
                } else {
                    coup = ihm.demanderCoupPuissance4(partie.getJoueurCourant().getNom());
                }

                if (!partie.verifierCoup(coup)) {
                    // coup invalide
                } else {
                    partie.jouerCoup(coup);
                    if (partie.aUnGagnant()) {
                        ihm.afficherGrille(partie.getGrille());
                        ihm.afficherGagnant(partie.getJoueurCourant().getNom());
                        partie.getJoueurCourant().incrementerPartiesGagnees();
                    } else {
                        partie.changerJoueur();
                    }
                }
            }

            if (partie.estGrillePleine() && !partie.aUnGagnant()) {
                ihm.afficherNul();
            }

            rejouer = ihm.demanderRejouer();
        }

        // 4. Afficher scores finaux
        ihm.afficherScores(nomJ1, nomJ2,
                joueur1.getNbPartiesGagnees(),
                joueur2.getNbPartiesGagnees());
    }
}
