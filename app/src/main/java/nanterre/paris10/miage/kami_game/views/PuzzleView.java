package nanterre.paris10.miage.kami_game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nanterre.paris10.miage.kami_game.util.Rectangle;

/**
 * Created by Mamadou BABA on 21/02/2018.
 */

public class PuzzleView extends View {

    private final Context context;
    private int largeur;
    private int hauteur;
    private int x;
    private int y;
    private int[] puzzle;
    private Rect rect ;
    private Paint paint;
    private Rectangle myRectangle;
    private List<Rectangle> allRectangle = new ArrayList<>();

    public PuzzleView(Context context, int[] puzzle) {
        super(context);
        this.context = context;
        this.puzzle = puzzle;
        rect = new Rect();
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        hauteur = h / 16; // 16
        largeur = w / 16; // 10
        //setRect(x, y);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int j = 0; j < 16; j++) {
            for(int k = 0; k < 16; k++){
               setRect(j,k);
                x = j;
                y = k;
               if(puzzle[k] == 1){
                  paint.setColor(Color.GREEN);
                  canvas.drawRect(rect, paint);
                   myRectangle = new Rectangle(x * largeur, y * hauteur, x * largeur + largeur, y * hauteur + hauteur, k);
                   allRectangle.add(myRectangle);
               }
               else {
                   paint.setColor(Color.YELLOW);
                   canvas.drawRect(rect, paint);
                   myRectangle = new Rectangle(x * largeur, y * hauteur, x * largeur + largeur, y * hauteur + hauteur, k);
                   allRectangle.add(myRectangle);
               }
            }
        }
    }

    private void setRect(int x, int y) {
        rect.set(x * largeur, y * hauteur, x * largeur + largeur, y * hauteur + hauteur);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(context, "item X : " + (int)event.getX() + " item Y : " +(int)event.getY(), Toast.LENGTH_LONG).show();
        changerCouleur(event.getX(), event.getY());

        return super.onTouchEvent(event);
    }

    private boolean changerCouleur(float itemX, float itemY) {

        // TODO : A REVOIR ABSOLUMENT
        //for (Rectangle r : allRectangle){
            //System.out.println("MyRect x : "+r.getX()+ " y : "+r.getY());
            if(1==1/*r.getX() == (int) itemX && r.getY() == (int) itemY*/){
                int valeur = 6;//r.getValeur();
                for(int i = 0; i < puzzle.length ; i++){
                   // if(puzzle[i] == puzzle[valeur] && puzzle[valeur] == 1 && i != valeur)
                        puzzle[i] = 2;
                    //if(puzzle[i] == puzzle[valeur] && puzzle[valeur] == 2 && i != valeur)
                      //  puzzle[i] = 1;
                }
            }
        //}
        if(isWin())
            Toast.makeText(context, "You Win !" , Toast.LENGTH_LONG).show();

        invalidate();

        return true;
    }

    private boolean isWin(){
        boolean win = true;
        int compare = puzzle[0];
        for(int i = 0; i < puzzle.length; i++){
            if(puzzle[i] != compare) win = false;
        }

        return win;
    }
}
