package com.example.school_app;


import static java.lang.String.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<UserModel> usersList;

    public LeaderboardAdapter() {
        usersList = new ArrayList<>();
    }

    /**
     * @param usersList
     */
    public void setUsersList(List<UserModel> usersList) {
        this.usersList = usersList;
        notifyDataSetChanged();
    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel user = usersList.get(position);
        holder.tvEmail.setText(user.getEmail());
        holder.tvCorrectAnswers.setText(valueOf(user.getTotalCorrectAnswers()));
        holder.tvAverageAccuracy.setText(String.format("%.2f%%", user.getAccuracy()));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvEmail;
        private final TextView tvCorrectAnswers;

        private final TextView tvAverageAccuracy;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvCorrectAnswers = itemView.findViewById(R.id.tvCorrectAnswers);
            tvAverageAccuracy = itemView.findViewById(R.id.tvAverageAccuracy);
        }
    }
}

