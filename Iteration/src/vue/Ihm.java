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
        System.out.println(nomJoueur + " a votre tour de jouer (ligne, colonne) : ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        return  new CoupMorpion(x-1, y-1);
    }

    public CoupPuissance4 demanderCoupPuissance4(String nomJoueur){
        System.out.println(nomJoueur + " : votre tour de joeur le coup (colonne) : ");
        int colonne = sc.nextInt() - 1;
        return new CoupPuissance4(colonne);
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
                char c = grille[i][j] == '\0' ? '.' : grille[i][j];
                System.out.print(c + " ");
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
}
