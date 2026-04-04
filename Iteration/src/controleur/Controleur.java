package controleur;

import modele.*;
import vue.Ihm;

public class Controleur {
    private Ihm ihm;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    public void lancerJeu() {
        // Demander le mode
        boolean contreIA = ihm.demanderModeJeu();

        // Demander les noms
        String nom1 = ihm.demanderLeNom(1);
        Joueur joueur1 = new Joueur(nom1);
        Joueur joueur2;

        if (!contreIA) {
            String nom2 = ihm.demanderLeNom(2);
            joueur2 = new Joueur(nom2);
        } else {
            joueur2 = new Joueur("IA");
        }

        // Demander le choix du jeu
        int choix = ihm.demanderChoixJeu();

        // Boucle de parties
        boolean rejouer = true;
        while (rejouer) {
            Partie partie;

            if (choix == 1) {
                Morpion morpion = new Morpion(joueur1, joueur2);
                if (contreIA) morpion.activerIA();
                partie = morpion;
            } else {
                Puissance4 puissance4 = new Puissance4(joueur1, joueur2);
                if (contreIA) puissance4.activerIA();
                partie = puissance4;
            }

            // Boucle d'une partie
            while (!partie.estTerminee()) {
                ihm.afficherGrille(partie.getGrille());

                Coup coup = null;

                // Si c'est le tour de l'IA
                if (contreIA && partie.getJoueurCourant() == joueur2) {
                    ihm.afficherTourIA();
                    coup = partie.getCoupIA();
                } else {
                    // Demander un coup valide au joueur humain
                    while (coup == null || !partie.verifierCoup(coup)) {
                        if (choix == 1) {
                            coup = ihm.demanderCoupMorpion(partie.getJoueurCourant().getNom());
                        } else {
                            coup = ihm.demanderCoupPuissance4(partie.getJoueurCourant().getNom());
                        }
                        if (coup != null && !partie.verifierCoup(coup)) {
                            ihm.afficherCoupInvalide();
                        }
                    }
                }

                partie.jouerCoup(coup);

                if (partie.aUnGagnant()) {
                    ihm.afficherGrille(partie.getGrille());
                    ihm.afficherGagnant(partie.getJoueurCourant().getNom());
                    partie.getJoueurCourant().incrementerPartiesGagnees();
                } else if (!partie.estTerminee()) {
                    partie.changerJoueur();
                }
            }

            if (partie.estGrillePleine() && !partie.aUnGagnant()) {
                ihm.afficherNul();
            }

            rejouer = ihm.demanderRejouer();
        }

        // Afficher scores finaux
        ihm.afficherScores(joueur1.getNom(), joueur2.getNom(),
                joueur1.getNbPartiesGagnees(),
                joueur2.getNbPartiesGagnees());
    }
}