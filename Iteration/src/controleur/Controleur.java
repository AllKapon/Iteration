package controleur;

import modele.*;
import vue.Ihm;

import javax.swing.*;

public class Controleur {
    private Ihm ihm;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    public void lancerJeu() {
        // Demander Mode du jeu
        boolean choixMode = ihm.demanderModeJeu();

        // Demander les noms et créer les joueurs
        String nom1 = ihm.demanderLeNom(1);
        Joueur joueur1 = new Joueur(nom1);
        Joueur joueur2;

        if(!choixMode){
            String nom2 = ihm.demanderLeNom(2);
            joueur2 = new Joueur(nom2);
        }
        else{
            joueur2 = new Joueur("IA");
        }

        // Demander le choix du jeu
        int choix = ihm.demanderChoixJeu();

        // Boucle de partie
        boolean rejouer = true;
        while (rejouer) {
            Partie partie;
            IAMorpion IAmorpion = null;
            IAPuissance4 IApuissance4 = null;

            if (choix == 1) {
                partie = new Morpion(joueur1, joueur2);
                IAmorpion = new IAMorpion((Morpion) partie);
            } else {
                partie = new Puissance4(joueur1, joueur2);
                IApuissance4 = new IAPuissance4((Puissance4) partie);
            }

            // Boucle d'une partie
            while (!partie.estTerminee()) {
                Coup coup = null;
                ihm.afficherGrille(partie.getGrille());

                if(choixMode && partie.getJoueurCourant() == joueur2){
                    if(choix == 1) {
                        ihm.afficherTourIA();
                        coup = IAmorpion.choisirCoup();
                    }
                    else {
                        ihm.afficherTourIA();
                        coup = IApuissance4.choisirCoup();
                    }
                }
                else {
                    // Demander un coup valide
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

        // Afficher resultats finales
        ihm.afficherScores(joueur1.getNom(), joueur2.getNom(),
                joueur1.getNbPartiesGagnees(),
                joueur2.getNbPartiesGagnees());
    }
}