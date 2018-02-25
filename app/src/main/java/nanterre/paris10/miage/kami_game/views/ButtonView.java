package nanterre.paris10.miage.kami_game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import nanterre.paris10.miage.kami_game.R;

/**
 * Created by Mamadou BABA on 24/02/2018.
 */

// Permet de gerer les buttons pour changer la couleur du puzzle
public class ButtonView extends View {

    private Paint paint;
    private Drawable drawable;

    public ButtonView(Context context) {
        super(context);
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.setBounds(getPaddingLeft(), getPaddingTop(), canvas.getWidth() - getPaddingRight(),
                canvas.getHeight() - getPaddingBottom());
        drawable.draw(canvas);
    }
}
