package modele;

public class CoupMorpion extends Coup{
    private int x, y;

    public CoupMorpion(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
