package com.example.school_app;

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

  public void setUsersList(List<UserModel> usersList) {
    this.usersList = usersList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    UserModel user = usersList.get(position);
    holder.tvEmail.setText(user.getEmail());
    holder.tvCorrectAnswers.setText(String.valueOf(user.getTotalCorrectAnswers()));
    holder.tvAverageAccuracy.setText(String.format("%.2f%%", user.getAccuracy() * 100));
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
