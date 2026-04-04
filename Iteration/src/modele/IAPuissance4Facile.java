package modele;

import java.util.Random;
import java.util.ArrayList;

public class IAPuissance4Facile extends IAPuissance4{
    private char symboleIA = 'B';
    private char symboleAdversaire = 'R';
    Random random;


    public IAPuissance4Facile(Puissance4 puissance4){
        super(puissance4);
        random = new Random();
    }

    @Override
    public CoupPuissance4 choisirCoup(){
        ArrayList<Integer> colonnesDispo = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            if (puissance4.getGrille()[0][i] == '\0') {
                colonnesDispo.add(i);
            }
        }

        int colonneChoisie = colonnesDispo.get(random.nextInt(colonnesDispo.size()));
        return new CoupPuissance4(colonneChoisie);
    }
}
