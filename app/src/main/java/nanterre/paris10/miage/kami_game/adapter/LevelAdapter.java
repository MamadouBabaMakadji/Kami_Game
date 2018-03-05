package nanterre.paris10.miage.kami_game.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nanterre.paris10.miage.kami_game.R;

/**
 * Created by Mamadou BABA on 16/02/2018.
 */

public class LevelAdapter extends BaseAdapter {

    private Context context;
    private String levels[];

    public LevelAdapter(Context context, String[] levels) {
        this.context = context;
        this.levels = levels;
    }

    @Override
    public int getCount() {
        return levels.length;
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
            view = inflater.inflate(R.layout.niveau, null);
        }
        ImageView imageView = view.findViewById(R.id.image);
        TextView textView = view.findViewById(R.id.niveau);
        imageView.setImageResource(R.drawable.puzzle);
        textView.setText("Niveau "+((getCount()+i+1) - getCount()));

        return view;
    }
}
