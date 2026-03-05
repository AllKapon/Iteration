package modele;

public class Morpion extends Partie{

    private char symboleJ1 = 'X';
    private char symboleJ2 = 'O';

    public Morpion(Joueur joueur1, Joueur joueur2){
        super(joueur1, joueur2, 3, 3);
    }

    @Override
    public boolean verifierCoup(Coup coup) {
        if(!((0 <= coup.getX()) && (coup.getX() <= getLigne()-1 ) && (0 <= coup.getY()) && (coup.getY() <= getColonne()))){
            return false;
        }
        if (getGrille()[coup.getX()][coup.getY()] != '\0')
            return false;
        return true;
    }

    @Override
    public void jouerCoup(Coup coup){
        char symbole = getJoueurCourant() == getJoueur1() ? symboleJ1 : symboleJ2;
        getGrille()[coup.getX()][coup.getY()] = symbole;
    }

    @Override
    public boolean aUnGagnant(){

    }
}
