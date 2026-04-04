package modele;

import java.util.ArrayList;
import java.util.Random;

public class IAMorpionFacile extends IAMorpion{

    Random random;

    public IAMorpionFacile(Morpion morpion){
        super(morpion);
        random = new Random();
    }

    @Override
    public CoupMorpion choisirCoup(){
        ArrayList<CoupMorpion> coupPossible = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (morpion.getGrille()[i][j] == '\0')
                    coupPossible.add(new CoupMorpion(i, j));
            }
        }
        return coupPossible.get(random.nextInt(coupPossible.size()));
    }
}
