package modele;

public class Puissance4 extends Partie{

    private char symboleJ1 = 'B';
    private char symboleJ2 = 'R';

    public Puissance4(Joueur joueur1,  Joueur joueur2){
        super(joueur1, joueur2, 6, 7);
    }

    @Override
    public boolean verifierCoup(Coup coup) {
        CoupPuissance4 coupPuissance4 = (CoupPuissance4) coup;

        if(!((0 <= coupPuissance4.getColonne()) && (coupPuissance4.getColonne() < getColonne()))){
            return false;
        }

        if (getGrille()[0][coupPuissance4.getColonne()] != '\0')
            return false;
        return true;

        /*
        int ligne=0;
        boolean trouvé=false;
        int i=0;
        while (i<getLigne() && trouvé==false ){
            i=0;
            if (getGrille()[0][i]=='\0'){
                ligne=i;
                trouvé=true;
            }
            else {
                i+=1;
            }

        }

        if (getGrille()[ligne][coupPuissance4.getColonne()] != '\0')
            return false;
        return true;
        */
    }

    @Override
    public void jouerCoup(Coup coup) {
        CoupPuissance4 coupPuissance4 = (CoupPuissance4) coup;
        char symbole = getJoueurCourant() == getJoueur1() ? symboleJ1 : symboleJ2;

        int ligne = getLigne() - 1;
        while (getGrille()[ligne][coupPuissance4.getColonne()] != '\0') {
            ligne--;
        }

        getGrille()[ligne][coupPuissance4.getColonne()] = symbole;
    }

    @Override
    public boolean aUnGagnant() {
        char[][] grille = getGrille();

        // Horizontales
        for (int i = 0; i < getLigne(); i++) {
            for (int j = 0; j <= getColonne() - 4; j++) {
                if (grille[i][j] != '\0' &&
                        grille[i][j] == grille[i][j+1] &&
                        grille[i][j+1] == grille[i][j+2] &&
                        grille[i][j+2] == grille[i][j+3]) {
                    return true;
                }
            }
        }

        // Verticales
        for (int i = 0; i <= getLigne() - 4; i++) {
            for (int j = 0; j < getColonne(); j++) {
                if (grille[i][j] != '\0' &&
                        grille[i][j] == grille[i+1][j] &&
                        grille[i+1][j] == grille[i+2][j] &&
                        grille[i+2][j] == grille[i+3][j]) {
                    return true;
                }
            }
        }

        // Diagonale principale
        for (int i = 0; i <= getLigne() - 4; i++) {
            for (int j = 0; j <= getColonne() - 4; j++) {
                if (grille[i][j] != '\0' &&
                        grille[i][j] == grille[i+1][j+1] &&
                        grille[i+1][j+1] == grille[i+2][j+2] &&
                        grille[i+2][j+2] == grille[i+3][j+3]) {
                    return true;
                }
            }
        }

        // Diagonale secondaire
        for (int i = 0; i <= getLigne() - 4; i++) {
            for (int j = 3; j < getColonne(); j++) {
                if (grille[i][j] != '\0' &&
                        grille[i][j] == grille[i+1][j-1] &&
                        grille[i+1][j-1] == grille[i+2][j-2] &&
                        grille[i+2][j-2] == grille[i+3][j-3]) {
                    return true;
                }
            }
        }

        return false;
    }

}