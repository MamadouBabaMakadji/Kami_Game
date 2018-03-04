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

public class ChoixCouleurAdapter extends BaseAdapter {

    private Context context;
    private int[] listColors;

    public ChoixCouleurAdapter(Context context, int[] listColors) {
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
            view = inflater.inflate(R.layout.button, null);
        }
        ImageView buttonView = view.findViewById(R.id.buttonView);
        if(listColors[i] == 1){
            buttonView.setBackgroundColor(Color.BLUE);
        }
        else if(listColors[i] == 2) {
            buttonView.setBackgroundColor(Color.YELLOW);
        }
        else if(listColors[i] == 3) {
            buttonView.setBackgroundColor(Color.GRAY);

        }
        else if(listColors[i] == 4) {
            buttonView.setBackgroundColor(Color.GREEN);
        }

        return view;
    }
}
