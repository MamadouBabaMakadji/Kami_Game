package nanterre.paris10.miage.kami_game.util;

/**
 * Created by Mamadou BABA on 27/02/2018.
 */

// Class permettant de récupérer chaque rectangle du puzzle ainsi que la valeur de la couleur
public class Rectangle {

    private int x;
    private int y;
    private int largeur;
    private int hauteur;
    private int valeur ;

    public Rectangle(int x, int y, int largeur, int hauteur, int valeur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.valeur = valeur;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
