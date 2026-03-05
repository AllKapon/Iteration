package modele;

public class Joueur {
    private String nom;
    private int nbPartiesGagnees;

    public Joueur(String nom){
        this.nom = nom;
        nbPartiesGagnees = 0;
    }

    public int getNbPartiesGagnees(){
        return nbPartiesGagnees;
    }

    public void incrementerPartiesGagnees(){
        nbPartiesGagnees++;
    }

    public String getNom(){
        return this.nom;
    }


}
