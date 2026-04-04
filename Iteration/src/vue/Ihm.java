package vue;

import modele.CoupMorpion;
import modele.CoupPuissance4;

import java.util.Scanner;

public class Ihm {

    Scanner sc = new Scanner(System.in);

    public String demanderLeNom(int numeroJoueur){
        System.out.println("Joueur " + numeroJoueur + " entrez votre nom: ");
        return sc.nextLine();
    }

    public int demanderChoixJeu(){
        System.out.println("Choisissez un jeu : ");
        System.out.println("1 - Morpion");
        System.out.println("2 - Puissance");
        return sc.nextInt();
    }

    public CoupMorpion demanderCoupMorpion(String nomJoueur){
        System.out.println(nomJoueur + " à votre tour (ligne colonne) : ");
        try {
            int x = sc.nextInt();
            int y = sc.nextInt();
            return new CoupMorpion(x-1, y-1);
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Saisie invalide !");
            return null;
        }
    }

    public void afficherCoupInvalide() {
        System.out.println("Coup invalide, réessayez !");
    }

    public CoupPuissance4 demanderCoupPuissance4(String nomJoueur) {
        System.out.println(nomJoueur + " : votre tour de jouer le coup (colonne 1-7) : ");
        try {
            int colonne = sc.nextInt() - 1;
            return new CoupPuissance4(colonne);
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Saisie invalide !");
            return null;
        }
    }

    public void afficherGagnant(String nomJoueur){
        System.out.println(nomJoueur + " a gagné!");
    }

    public boolean demanderRejouer(){
        System.out.println("Voulez vous rejouer ? (o, n) : ");
        String reponse = sc.next();
        return reponse.equals("o");
    }

    public void afficherGrille(char[][] grille) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (grille[i][j] == 'B') {
                    System.out.print("\u001B[34m●\u001B[0m ");
                } else if (grille[i][j] == 'R') {
                    System.out.print("\u001B[31m●\u001B[0m ");
                } else if (grille[i][j] == '\0') {
                    System.out.print(". ");
                } else {
                    System.out.print(grille[i][j] + " "); // X et O
                }
            }
            System.out.println();
        }
    }

    public void afficherScores(String nomJ1, String nomJ2, int scoreJ1, int scoreJ2){
        System.out.println("Score finaux: ");
        System.out.println(nomJ1 + " : " + scoreJ1);
        System.out.println(nomJ2 + " : " + scoreJ2);

        if (scoreJ1 > scoreJ2) System.out.println(nomJ1 + " a gagné!");
        else if (scoreJ2 > scoreJ1) System.out.println(nomJ2 + " a gagné!");
        else System.out.println("===Ex aeque!===");
    }

    public void afficherNul() {
        System.out.println("Partie nulle !");
    }

    // demander mode de jeu -> contre IA ou joueur
    public boolean demanderModeJeu(){
        System.out.println("1 - Jouer à deux joueurs");
        System.out.println("2 - Jouer contre IA");
        int choix = sc.nextInt();
        sc.nextLine();
        return choix == 2;
    }
/*
    public void afficherTourJoueur(String nomJoueur){
        System.out.println("--- Tour de joueur " + nomJoueur + " ---");
    }
*/
    public void afficherTourIA(){
        System.out.println("--- Tour de IA ---");
    }

    public boolean demanderNiveau() {
        System.out.println("Choisissez le niveau : ");
        System.out.println("1 - Facile");
        System.out.println("2 - Difficile");
        int choix = sc.nextInt();
        sc.nextLine();
        return choix == 2;
    }
}
