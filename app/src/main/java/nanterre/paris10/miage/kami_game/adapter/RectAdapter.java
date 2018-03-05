package nanterre.paris10.miage.kami_game.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import nanterre.paris10.miage.kami_game.R;

/**
 * Created by MAKADJI Mamadou Baba on 02/03/2018.
 */

public class RectAdapter extends BaseAdapter {

    private Context context;
    private int[] listColors;

    public RectAdapter(Context context, int[] listColors) {
        this.context = context;
        this.listColors = listColors;
    }

    @Override
    public int getCount() {
        return listColors.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid, null);
        }
        ImageView myRectView = view.findViewById(R.id.myRectView);
        if(listColors[i] == 1){
            myRectView.setBackgroundColor(Color.RED);
        }
        else if(listColors[i] == 2) {
            myRectView.setBackgroundColor(Color.YELLOW);
        }
        else if(listColors[i] == 3) {
            myRectView.setBackgroundColor(Color.GRAY);

        }
        else if(listColors[i] == 4) {
            myRectView.setBackgroundColor(Color.GREEN);
        }

        return view;
    }
}
