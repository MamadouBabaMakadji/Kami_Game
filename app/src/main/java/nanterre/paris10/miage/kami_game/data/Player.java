package nanterre.paris10.miage.kami_game.data;

/**
 * Created by MAKADJI Mamadou Baba on 28/02/2018.
 */
// Cette classe est le modèle permettant de faire des insertions dans notre base de données
public class Player {
    private long _id;
    private String name;
    private byte[] image;
    private int score;
    private int niveau;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
}
