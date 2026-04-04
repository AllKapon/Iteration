package modele;

public class IAMorpion {
    private Morpion morpion;
    private char symboleIA = 'O';
    private char symboleAdversaire = 'X';


    public IAMorpion(Morpion morpion){
        this.morpion = morpion;
    }

    public CoupMorpion choisirCoup(){

        int meilleurScore = -2;
        int score;

        if(compterCasesVides() <= 4){
            meilleurScore = -2;
            CoupMorpion meilleurCoup = null;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(morpion.getGrille()[i][j] == '\0') {
                        morpion.getGrille()[i][j] = symboleIA;
                        score = minMax(false);
                        morpion.getGrille()[i][j] = '\0';
                        if (score > meilleurScore) {
                            meilleurScore = score;
                            meilleurCoup = new CoupMorpion(i, j);
                        }
                    }
                }
            }
            return meilleurCoup;
        }
        else {
            // Règle 1: L'IA peut gagner et jouer le coup ?
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (morpion.getGrille()[i][j] == '\0') {
                        morpion.getGrille()[i][j] = symboleIA;
                        if (morpion.aUnGagnant()) {
                            morpion.getGrille()[i][j] = '\0';
                            return new CoupMorpion(i, j);
                        }
                        morpion.getGrille()[i][j] = '\0';
                    }
                }
            }

            // Regle 2: Bloquer l'adversaire
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (morpion.getGrille()[i][j] == '\0') {
                        morpion.getGrille()[i][j] = symboleAdversaire;
                        if (morpion.aUnGagnant()) {
                            morpion.getGrille()[i][j] = '\0';
                            return new CoupMorpion(i, j);
                        }
                        morpion.getGrille()[i][j] = '\0';
                    }
                }
            }

            // Règle 3: Prendre le centre
            if (morpion.getGrille()[1][1] == '\0')
                return new CoupMorpion(1, 1);

            // Règle 4: prendre un coin
            if (morpion.getGrille()[0][0] == '\0')
                return new CoupMorpion(0, 0);

            if (morpion.getGrille()[0][2] == '\0')
                return new CoupMorpion(0, 2);

            if (morpion.getGrille()[2][0] == '\0')
                return new CoupMorpion(2, 0);

            if (morpion.getGrille()[2][2] == '\0')
                return new CoupMorpion(2, 2);

            // Règle 5: prendre un côté
            if (morpion.getGrille()[0][1] == '\0')
                return new CoupMorpion(0, 1);

            if (morpion.getGrille()[1][0] == '\0') {
                return new CoupMorpion(1, 0);
            }

            if (morpion.getGrille()[1][2] == '\0')
                return new CoupMorpion(1, 2);

            if (morpion.getGrille()[2][1] == '\0')
                return new CoupMorpion(2, 1);

            // Règle 6: prendre la premier case vide
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (morpion.getGrille()[i][j] == '\0')
                        return new CoupMorpion(i, j);
                }
            }
        }
        return null;
    }

    public int minMax(boolean estTourIA){
        int meilleurScore = -2;
        int score = 0;

        if(morpion.aUnGagnant() && !estTourIA) return +1;

        if(morpion.aUnGagnant() && estTourIA) return -1;

        if(morpion.estGrillePleine()) return 0;

        if(estTourIA){
            meilleurScore = -2;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++) {
                    if (morpion.getGrille()[i][j] == '\0') {
                        morpion.getGrille()[i][j] = symboleIA;
                        score = minMax(false);
                        morpion.getGrille()[i][j] = '\0';
                        meilleurScore = Math.max(meilleurScore, score);
                    }
                }
            }
            return meilleurScore;
        }
        else {
            meilleurScore = +2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (morpion.getGrille()[i][j] == '\0') {
                        morpion.getGrille()[i][j] = symboleAdversaire;
                        score = minMax(true);
                        morpion.getGrille()[i][j] = '\0';
                        meilleurScore = Math.min(meilleurScore, score);
                    }
                }
            }
            return meilleurScore;
        }
    }

    public int compterCasesVides(){
        int compteur = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j<3; j++){
                if (morpion.getGrille()[i][j] == '\0') {
                    compteur++;
                }
            }
        }
        return compteur;
    }
}
