package modele;

public abstract class IAMorpion {
    protected Morpion morpion;

    public IAMorpion(Morpion morpion){
        this.morpion = morpion;
    }

    public abstract CoupMorpion choisirCoup();
}
