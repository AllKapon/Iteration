package modele;

public abstract class Partie {
    private Joueur joueur1, joueur2;
    private Joueur joueurCourant;
    private char[][] grille;

    private int ligne;
    private int colonne;

    public Partie(Joueur joueur1, Joueur joueur2, int ligne, int colonne){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        joueurCourant = joueur1;

        this.ligne = ligne;
        this.colonne = colonne;

        grille = new char[ligne][colonne];
    }

    public abstract boolean verifierCoup(Coup coup);

    public abstract void jouerCoup(Coup coup);

    public abstract boolean aUnGagnant();

    // pour l'IA
    public abstract boolean aUneIA();

    public abstract void activerIA(boolean difficile);

    public abstract Coup getCoupIA();

    public boolean estTerminee() {
        return aUnGagnant() || estGrillePleine();
    }

    public void changerJoueur(){
        joueurCourant = joueurCourant == joueur1 ? joueur2 : joueur1;
    }

    public boolean estGrillePleine(){
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {

                // On a regardé avec IA comment voir les cases vide
                if (grille[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public Joueur getJoueurCourant(){
        return joueurCourant;
    }

    public Joueur getJoueur1(){
        return joueur1;
    }
    public Joueur getJoueur2(){
        return joueur2;
    }

    public char[][] getGrille() {
        return grille;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }
}
