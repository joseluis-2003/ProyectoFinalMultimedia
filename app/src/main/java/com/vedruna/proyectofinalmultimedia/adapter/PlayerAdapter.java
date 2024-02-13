package com.vedruna.proyectofinalmultimedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.proyectofinalmultimedia.R;
import com.vedruna.proyectofinalmultimedia.model.Player;

import java.util.List;
/**
 * PlayerAdapter class is a custom adapter for populating a ListView with Player objects.
 * It extends the BaseAdapter class.
 */

public class PlayerAdapter extends BaseAdapter {
    private List<Player> playerList;
    private Context context;
    /**
     * Constructs a PlayerAdapter with the given context and player list.
     *
     * @param context    The context in which the adapter is being used.
     * @param playerList The list of Player objects to be displayed.
     */

    public PlayerAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
    }
    /**
     * Returns the number of items in the adapter.
     *
     * @return The number of items in the adapter, which is the size of the playerList.
     */
    @Override
    public int getCount() {
        return playerList.size();
    }
    /**
     * Returns the data item at the specified position in the adapter.
     *
     * @param position The position of the item within the adapter's data set.
     * @return The data item at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return playerList.get(position);
    }
    /**
     * Returns the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id is sought.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return playerList.get(position).getIdPlayer();
    }
    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.player_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.idLabel = convertView.findViewById(R.id.idLabel);
            viewHolder.idText = convertView.findViewById(R.id.idText);
            viewHolder.nameLabel = convertView.findViewById(R.id.nameLabel);
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.positionLabel = convertView.findViewById(R.id.positionLabel);
            viewHolder.positionText = convertView.findViewById(R.id.positionText);
            viewHolder.equipoLabel = convertView.findViewById(R.id.equipoLabel);
            viewHolder.equipoText = convertView.findViewById(R.id.equipoText);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtén el producto actual
        Player player = (Player) getItem(position);

        // Asignar los valores del producto a las vistas
        viewHolder.idLabel.setText("Id: ");
        viewHolder.idText.setText(String.valueOf(player.getIdPlayer()));
        viewHolder.nameLabel.setText("Nombre: ");
        viewHolder.nameText.setText(player.getName());
        viewHolder.positionLabel.setText("Posicion: ");
        viewHolder.positionText.setText(String.valueOf(player.getPosition()));
        viewHolder.equipoLabel.setText("Equipo: ");
        viewHolder.equipoText.setText(String.valueOf(player.getClubName()));

        return convertView;
    }
    /**
     * ViewHolder class to hold the views of each item in the ListView to avoid unnecessary findViewById() calls.
     */
    static class ViewHolder {
        TextView idLabel;
        TextView idText;
        TextView nameLabel;
        TextView nameText;
        TextView positionLabel;
        TextView positionText;
        TextView equipoLabel;
        TextView equipoText;
    }
}
