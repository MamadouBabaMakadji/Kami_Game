package nanterre.paris10.miage.kami_game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.GridView;

import nanterre.paris10.miage.kami_game.R;

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
    private Rect rect = new Rect();
    private Drawable drawable;

    public PuzzleView(Context context, int[] puzzle) {
        super(context);
        this.context = context;
        this.puzzle = puzzle;
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_cell);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        hauteur = h / 16; // 16
        largeur = w / 16; // 10
        setRect(x, y);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint gray = new Paint();
        Paint green = new Paint();
        Paint orange = new Paint();
        Paint blue = new Paint();
        gray.setColor(Color.GRAY);
        green.setColor(Color.GREEN);
        orange.setColor(Color.YELLOW);
        blue.setColor(Color.BLUE);

        for(int j = 0; j < 16; j++) {
            for(int k = 0; k < 10; k++){
               setRect(j,k);
                drawable.setBounds(getPaddingLeft(), getPaddingTop(), canvas.getWidth() - getPaddingRight(),
                        canvas.getHeight() - getPaddingBottom());
                drawable.draw(canvas);
                x = j;
                y = k;
               if(puzzle[k] == 1)
                   canvas.drawRect(rect, orange);
               else canvas.drawRect(rect, blue);
            }
        }
    }

    private void setRect(int x, int y) {
        rect.set(x * largeur, y * hauteur, x * largeur + largeur, y * hauteur + hauteur);
    }

}
