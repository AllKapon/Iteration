package modele;

import java.util.ArrayList;
import java.util.Random;

public class IAPuissance4Difficile extends IAPuissance4{
    private Random random;

    public IAPuissance4Difficile(Puissance4 puissance4){
        super(puissance4);
        random = new Random();
    }

    @Override
    public CoupPuissance4 choisirCoup() {
        int[] scores = new int[7];

        char symboleIA = puissance4.getJoueurCourant() == puissance4.getJoueur2() ? 'B' : 'R';
        char symboleAdversaire = symboleIA == 'B' ? 'R' : 'B';

        // Bonus de position
        int[] bonusPosition = {1, 2, 3, 5, 3, 2, 1};
        for (int j = 0; j < 7; j++){
            if (puissance4.getGrille()[0][j] == '\0'){
                scores[j] += bonusPosition[j];
            }
        }

        // Phase offensive et défensive
        for (int j = 0; j < 7; j++) {
            if (puissance4.getGrille()[0][j] == '\0') {
                int ligne = puissance4.getLigne() - 1;
                while (puissance4.getGrille()[ligne][j] != '\0') {
                    ligne--;
                }

                // +1000 si l'IA peut gagner
                puissance4.getGrille()[ligne][j] = symboleIA;
                if (puissance4.aUnGagnant()) {
                    scores[j] += 1000;
                }
                puissance4.getGrille()[ligne][j] = '\0';

                // +900 si bloquer l'adversaire
                puissance4.getGrille()[ligne][j] = symboleAdversaire;
                if (puissance4.aUnGagnant()) {
                    scores[j] += 900;
                }
                puissance4.getGrille()[ligne][j] = '\0';
            }
        }

        // Évaluation des fenêtres de 4 cases
        char[][] grille = puissance4.getGrille();
        int lignes = puissance4.getLigne();
        int colonnes = puissance4.getColonne();

        // Horizontales
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j <= colonnes - 4; j++) {
                evaluerFenetre(grille, scores, symboleIA,
                        new int[]{i,i,i,i},
                        new int[]{j,j+1,j+2,j+3});
            }
        }

        // Verticales
        for (int i = 0; i <= lignes - 4; i++) {
            for (int j = 0; j < colonnes; j++) {
                evaluerFenetre(grille, scores, symboleIA,
                        new int[]{i,i+1,i+2,i+3},
                        new int[]{j,j,j,j});
            }
        }

        // Diagonale principale
        for (int i = 0; i <= lignes - 4; i++) {
            for (int j = 0; j <= colonnes - 4; j++) {
                evaluerFenetre(grille, scores, symboleIA,
                        new int[]{i,i+1,i+2,i+3},
                        new int[]{j,j+1,j+2,j+3});
            }
        }

        // Diagonale secondaire
        for (int i = 0; i <= lignes - 4; i++) {
            for (int j = 3; j < colonnes; j++) {
                evaluerFenetre(grille, scores, symboleIA,
                        new int[]{i,i+1,i+2,i+3},
                        new int[]{j,j-1,j-2,j-3});
            }
        }

        // Trouver le score max
        int scoreMax = -1;
        for (int j = 0; j < 7; j++){
            if (scores[j] > scoreMax) scoreMax = scores[j];
        }

        // Collecter les colonnes avec le score max
        ArrayList<Integer> meilleursCoups = new ArrayList<>();
        for (int j = 0; j < 7; j++){
            if (scores[j] == scoreMax) meilleursCoups.add(j);
        }

        // Choisir aléatoirement
        int colonne = meilleursCoups.get(random.nextInt(meilleursCoups.size()));
        return new CoupPuissance4(colonne);
    }

    private void evaluerFenetre(char[][] grille, int[] scores, char symboleIA,
                                int[] lignes, int[] colonnes) {
        int countIA = 0;
        int countVide = 0;
        ArrayList<Integer> colonnesVides = new ArrayList<>();

        for (int k = 0; k < 4; k++) {
            if (grille[lignes[k]][colonnes[k]] == symboleIA) {
                countIA++;
            } else if (grille[lignes[k]][colonnes[k]] == '\0') {
                countVide++;
                colonnesVides.add(colonnes[k]);
            }
        }

        // +100 par espace vide si 3 pions IA alignés
        if (countIA == 3 && countVide == 1) {
            for (int col : colonnesVides) {
                scores[col] += 100;
            }
        }

        // +10 par groupe de 2 pions IA
        if (countIA == 2 && countVide == 2) {
            ArrayList<Integer> dejaCounts = new ArrayList<>();
            for (int col : colonnesVides) {
                if (!dejaCounts.contains(col)) {
                    scores[col] += 10;
                    dejaCounts.add(col);
                }
            }
        }
    }
}