package modele;

public abstract class IAPuissance4 {
    protected Puissance4 puissance4;

    public IAPuissance4(Puissance4 puissance4){
        this.puissance4 = puissance4;
    }

    public abstract CoupPuissance4 choisirCoup();
}
