package com.example.submission3.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.submission3.R;
import com.example.submission3.model.MovieData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {
    private final ArrayList<MovieData> movieData = new ArrayList<>();

    public void setMovieData(ArrayList<MovieData> itemData) {
        movieData.clear();
        movieData.addAll(itemData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.CardViewViewHolder cardViewViewHolder, int i) {
        cardViewViewHolder.bind(movieData.get(i));
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgMovie;
        final TextView tvName;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.imageUpView);
            tvName = itemView.findViewById(R.id.tv_item_up);
        }

        void bind(MovieData movieData) {
            tvName.setText(movieData.getTitle());
            Glide.with(itemView).load(movieData.getPosterPath())
                    .into(imgMovie);
        }
    }
}
