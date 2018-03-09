package nanterre.paris10.miage.kami_game.util;

import java.util.ArrayList;

/**
 * Created by MAKADJI Mamadou Baba on 03/03/2018.
 */

// Class pour le jeu
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
       //algoParcours(actualColor, selectColor);
        changerVoisin(actualColor, selectColor);
    }

    // Va parcourir tout le puzzle et changer la case sélectionnée ainsi les cases reliées par la couleur selectionnée
    // Vas faire un parcours en largeur, profondeur ... pour détecter les cases à changer
    private void algoParcours (int actualColor, int selectColor){
        int couleur_a_changer = listColors[actualColor];
        for(int i = 0; i < listColors.length; i++){
            if(listColors[i] == couleur_a_changer) {
                int j = i;
                do{
                    listColors[j] = selectColor;
                    j++;
                }while(( j + 1 == actualColor || j - 1 == actualColor || j + 11 == actualColor || j + 10 == actualColor ||
                        j - 10 == actualColor || j - 11 == actualColor) );
            }
        }
    }

    // vérifie si une case a un voisin différent
    private void changerVoisin(int item, int selectColor){
        int couleur_a_changer = listColors[item];
        int indice = item;
        listColors[indice] = selectColor;
            if(indice < listColors.length -1 && couleur_a_changer == listColors[indice+1]){
                indice+=1;
                changerVoisin(indice, selectColor);
            }
            if (indice > 0 && couleur_a_changer == listColors[indice - 1]){
                indice-=1;
                changerVoisin(indice, selectColor);
            }
            if (indice < listColors.length - 11 && couleur_a_changer == listColors[indice+11]){
                indice+=11;
                changerVoisin(indice, selectColor);
            }
            if (indice > 11 && couleur_a_changer == listColors[indice - 11]){
                indice-=11;
                changerVoisin(indice, selectColor);
            }
            if (indice < listColors.length - 10 &&couleur_a_changer == listColors[indice + 10]){
                indice+=10;
                changerVoisin(indice, selectColor);
            }
            if (indice > 10 && couleur_a_changer == listColors[indice - 10]){
                indice -= 10;
                changerVoisin(indice, selectColor);
            }
    }

    public ArrayList<Integer> diffColorValue(){
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < listColors.length; i++){
            if(! res.contains(listColors[i]))
                res.add(listColors[i]);
        }

        return res;
    }

}
