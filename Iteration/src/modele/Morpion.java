package modele;

public class Morpion extends Partie{

    private char symboleJ1 = 'X';
    private char symboleJ2 = 'O';

    private IAMorpion IA;

    public Morpion(Joueur joueur1, Joueur joueur2){
        super(joueur1, joueur2, 3, 3);
    }

    @Override
    public boolean verifierCoup(Coup coup) {
        CoupMorpion coupMorpion = (CoupMorpion) coup;
        if(!((0 <= coupMorpion.getX()) && (coupMorpion.getX() <= getLigne()-1 ) && (0 <= coupMorpion.getY()) && (coupMorpion.getY() <= getColonne()))){
            return false;
        }
        if (getGrille()[coupMorpion.getX()][coupMorpion.getY()] != '\0')
            return false;
        return true;
    }

    @Override
    public void jouerCoup(Coup coup){
        CoupMorpion coupMorpion = (CoupMorpion) coup;
        char symbole = getJoueurCourant() == getJoueur1() ? symboleJ1 : symboleJ2;
        getGrille()[coupMorpion.getX()][coupMorpion.getY()] = symbole;
    }

    @Override
    public boolean aUnGagnant(){
        for(int i = 0; i < getLigne(); i++){
                if (getGrille()[i][0] == getGrille()[i][1] &&
                        getGrille()[i][1] == getGrille()[i][2] &&
                        getGrille()[i][0] != '\0'){
                    return true;
                }
        }
        for(int j = 0; j < getColonne(); j++){
            if (getGrille()[0][j] == getGrille()[1][j] &&
                    getGrille()[1][j] == getGrille()[2][j] &&
                    getGrille()[0][j] != '\0'){
                return true;
            }
        }

        // la diagonale principale -> de haut gauche vers le bas-droite
        if (getGrille()[0][0] == getGrille()[1][1] &&
                getGrille()[1][1] == getGrille()[2][2] &&
                getGrille()[0][0] != '\0')
            return true;

        // la diagonale secondaire -> de haute droite vers le bas-gauche
        if (getGrille()[0][2] == getGrille()[1][1] &&
                getGrille()[1][1] == getGrille()[2][0] &&
                getGrille()[0][2] != '\0')
            return true;

        return false;
    }

    public void activerIA(){
        this.IA = new IAMorpion(this);
    }

    @Override
    public boolean aUneIA(){
        return IA != null;
    }

    @Override
    public Coup getCoupIA(){
        return IA.choisirCoup();
    }
}
