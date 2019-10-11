package com.danielkarlkvist.padelbuddy.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielkarlkvist.padelbuddy.Model.Game;
import com.danielkarlkvist.padelbuddy.Model.Player;
import com.danielkarlkvist.padelbuddy.R;

import java.util.ArrayList;

/**
 * The GameToRecyclerViewAdapter class defines an adapter between a Game and a RecyclerView
 *
 * @author Robin Repo Wecklauf, Marcus Axelsson, Daniel Karlkvist
 * Carl-Johan Björnson och Fredrik Lilliecreutz
 * @version 1.0
 * @since 2019-10-05
 */

public class GameToRecyclerViewAdapter extends RecyclerView.Adapter<GameToRecyclerViewAdapter.GameAdViewHolder> {

    private ArrayList<Game> games;

    /**
     * The ViewHolder which should be updated to represent the contents of a Game.
     */

    static class GameAdViewHolder extends RecyclerView.ViewHolder {
        TextView locationTextView;
        TextView dateTextView;
        TextView skillLevelTextView;

        TextView[] playerNameTextViews = new TextView[4];
        ImageView[] playerImagesViews = new ImageView[4];
        RatingBar[] playerRatingBars = new RatingBar[4];

        GameAdViewHolder(@NonNull View itemView) {
            super(itemView);

            locationTextView = itemView.findViewById(R.id.location_textview);
            dateTextView = itemView.findViewById(R.id.date_textview);

            skillLevelTextView = itemView.findViewById(R.id.skilllevel_textview);

            playerImagesViews[0] = itemView.findViewById(R.id.player1_imageview);
            playerImagesViews[1] = itemView.findViewById(R.id.player2_imageview);
            playerImagesViews[2] = itemView.findViewById(R.id.player3_imageview);
            playerImagesViews[3] = itemView.findViewById(R.id.player4_imageview);

            playerNameTextViews[0] = itemView.findViewById(R.id.player1_name_textview);
            playerNameTextViews[1] = itemView.findViewById(R.id.player2_name_textview);
            playerNameTextViews[2] = itemView.findViewById(R.id.player3_name_textview);
            playerNameTextViews[3] = itemView.findViewById(R.id.player4_name_textview);

            playerRatingBars[0] = itemView.findViewById(R.id.player1_ratingbar);
            playerRatingBars[1] = itemView.findViewById(R.id.player2_ratingbar);
            playerRatingBars[2] = itemView.findViewById(R.id.player3_ratingbar);
            playerRatingBars[3] = itemView.findViewById(R.id.player4_ratingbar);

            for (RatingBar ratingBar : playerRatingBars) {
                ratingBar.setStepSize(0.1f);
            }
        }
    }

    GameToRecyclerViewAdapter(ArrayList<Game> games) {
        this.games = games;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @NonNull
    @Override
    public GameAdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        GameAdViewHolder gameAdViewHolder = new GameAdViewHolder(view);
        return gameAdViewHolder;
    }

    // Called by RecyclerView to display the data from Game at the specified position.
    @Override
    public void onBindViewHolder(@NonNull GameAdViewHolder holder, int position) {
        Game currentGame = games.get(position);
        // Set location
        holder.locationTextView.setText(currentGame.getLocation());
        // Set date
        holder.dateTextView.setText(currentGame.getDateAsString());
        // Set skill level
        holder.skillLevelTextView.setText("Nybörjare");

        // Set name and rating for all (4) players
        for (int i = 0; i < currentGame.getPlayers().length; i++) {
            Player player = currentGame.getPlayers()[i];
            if (player != null) {
                holder.playerNameTextViews[i].setText(player.getFirstname());
                holder.playerImagesViews[i].setImageResource(R.drawable.profile_picture);
                holder.playerRatingBars[i].setRating(player.getProfileRating());
            } else {
                holder.playerNameTextViews[i].setText("Tillgänglig");
                holder.playerImagesViews[i].setImageResource(R.drawable.waitning_for_player_picture);
            }
        }
    }

    // Returns the total number of items in the data set held by the adapter.
    @Override
    public int getItemCount() {
        return games.size();
    }
}
