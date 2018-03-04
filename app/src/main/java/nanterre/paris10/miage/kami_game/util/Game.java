package nanterre.paris10.miage.kami_game.util;

/**
 * Created by MAKADJI Mamadou Baba on 03/03/2018.
 */

// Class pour l'activité du jeu
public class Game {
    private int nb_coup_max;
    private int nb_coup;
    private int[] listColors;
    private int diffCouleur;
    private int score;
    private int niveau;

    public int getNb_coup_max() {
        return nb_coup_max;
    }

    public void setNb_coup_max(int nb_coup_max) {
        this.nb_coup_max = nb_coup_max;
    }

    public int getNb_coup() {
        return nb_coup;
    }

    public void setNb_coup(int nb_coup) {
        this.nb_coup = nb_coup;
    }

    public int[] getListColors() {
        return listColors;
    }

    public void setListColors(int[] listColors) {
        this.listColors = listColors;
    }

    public int getDiffCouleur() {
        return diffCouleur;
    }

    public void setDiffCouleur(int diffCouleur) {
        this.diffCouleur = diffCouleur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public boolean isWin(){
        boolean result = true;
        int i = 1;
        do {
            if(this.listColors[0] != this.listColors[i])
                result = false;
            i++;
        }while (result && i < this.listColors.length);

        return result;
    }

    public boolean isLose(boolean win){
        if(!win && this.nb_coup_max == this.nb_coup)
            return true;
        return false;
    }

    public void changeColor(int actualColor, int selectColor){
        // Changement du couleur des cases du côté
        parcoursCoteDroit(actualColor, selectColor);
        parcoursCoteGauche(actualColor, selectColor);
        // Changement du couleur des cases du bas
        if(actualColor < listColors.length - 10 && listColors[actualColor + 10] != selectColor)
            parcoursBas(actualColor, selectColor);
        // Changement du couleur des cases du haut
        if(actualColor >= 10 && listColors[actualColor - 10] != selectColor)
            parcoursHaut(actualColor, selectColor);
        //Changement du couleur des cases en oblique
        if(actualColor < listColors.length - 11 && listColors[actualColor + 11] != selectColor)
            parcoursObliqueBas(actualColor, selectColor);
        if(actualColor >= 11 && listColors[actualColor - 11] != selectColor)
            parcoursObliqueHaut(actualColor, selectColor);
    }
    // Les différents algo de parcours
    // 1- Parcours toutes les cases à partir de la case selectionnée j'usqu'à la dernière case de la même couleur
    private void parcoursCoteDroit(int actualColor, int selectColor){
        int k = actualColor;
        // Droite
        do {
            this.listColors[k] = selectColor;
            k++;
        }while(k < listColors.length && listColors[k] != selectColor);
    }

    private void parcoursCoteGauche(int actualColor, int selectColor){
        int k = actualColor;
        // Gauche
        do {
            this.listColors[k] = selectColor;
            k--;
        }while(k >= 0 && listColors[k] != selectColor);
    }

    // 2- Parcours toutes les cases à partir de la case selectionnée j'usqu'à la dernière case de la même couleur vers le bas
    private void parcoursBas(int actualColor, int selectColor){
        int k = actualColor;
        do {
            this.listColors[k] = selectColor;
            k += 10;
        }while(k < listColors.length && listColors[k] != selectColor);
    }

    // 3- Parcours toutes les cases à partir de la case selectionnée j'usqu'à la dernière case de la même couleur vers le haut
    private void parcoursHaut(int actualColor, int selectColor){
        int k = actualColor;
        do {
            this.listColors[k] = selectColor;
            k -= 10;
        }while(k >= 0 && listColors[k] != selectColor);
    }

    // 4- Parcours toutes les cases à partir de la case selectionnée j'usqu'à la dernière case de la même couleur obliquement
    private void parcoursObliqueBas(int actualColor, int selectColor){
        int k = actualColor;
        do {
            this.listColors[k] = selectColor;
            k += 11;
        }while(k < listColors.length && listColors[k] != selectColor);
    }
    private void parcoursObliqueHaut(int actualColor, int selectColor){
        int k = actualColor;
        do {
            this.listColors[k] = selectColor;
            k -= 11;
        }while(k >= 0 && listColors[k] != selectColor);
    }

}
