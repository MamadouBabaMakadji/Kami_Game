package nanterre.paris10.miage.kami_game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nanterre.paris10.miage.kami_game.R;

/**
 * Created by Mamadou BABA on 21/02/2018.
 */

public class PuzzleAdapter extends BaseAdapter {
    private Context context;
    private int[] puzzle;

    public PuzzleAdapter(Context context, int[] puzzle) {
        this.context = context;
        this.puzzle = puzzle;
    }

    @Override
    public int getCount() {
        return puzzle.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.cell,null);
        }
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(puzzle[i]);
        if (puzzle[i] == 1) {
            imageView.setBackgroundColor(Color.BLUE);
        } else{
            imageView.setBackgroundColor(Color.MAGENTA);
        }

        return view;
    }
}
