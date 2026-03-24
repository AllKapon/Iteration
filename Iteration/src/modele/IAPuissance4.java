package modele;

import java.util.Random;
import java.util.ArrayList;

public class IAPuissance4 {
    private Puissance4 puissance4;
    private char symboleIA = 'B';
    private char symboleAdversaire = 'R';
    Random random;

    public IAPuissance4(Puissance4 puissance4){
        this.puissance4 = puissance4;
        random = new Random();
    }

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
