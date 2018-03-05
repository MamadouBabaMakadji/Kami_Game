package nanterre.paris10.miage.kami_game.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.data.Player;

/**
 * Created by MAKADJI Mamadou Baba on 28/02/2018.
 */

public class PlayerAdapter extends BaseAdapter {

    private Context context;
    private List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.players, null);
        }
        ImageView player_img = view.findViewById(R.id.player_imageView);
        TextView player_name = view.findViewById(R.id.player_name);
        TextView player_score = view.findViewById(R.id.player_score);
        TextView player_niveau = view.findViewById(R.id.player_niveau);
        byte[] bytes = players.get(i).getImage();
        if(bytes == null){
            player_img.setImageResource(R.drawable.profile);
        }else {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            player_img.setImageBitmap(bitmap);
        }
        player_name.setText(players.get(i).getName());
        player_score.setText("Score : " + players.get(i).getScore());
        player_niveau.setText("Niveau : " + players.get(i).getNiveau());

        return view;
    }
}
