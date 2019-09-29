package com.example.submission3.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3.R;
import com.example.submission3.model.TVShowPopularData;

import java.util.ArrayList;

public class TVShowPopularAdapter extends RecyclerView.Adapter<TVShowPopularAdapter.CardViewViewHolder> {
    private final ArrayList<TVShowPopularData> tvPopularData = new ArrayList<>();

    public void setTVShowPopularData(ArrayList<TVShowPopularData> itemData) {
        tvPopularData.clear();
        tvPopularData.addAll(itemData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show_popular, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        cardViewViewHolder.bind(tvPopularData.get(i));
    }

    @Override
    public int getItemCount() {
        return tvPopularData.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        final ImageView posterTvPop;
        final TextView nameTvPop;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            posterTvPop = itemView.findViewById(R.id.poster_tv_pop);
            nameTvPop = itemView.findViewById(R.id.tv_name_pop);
        }

        void bind(TVShowPopularData tvPopularData) {
            nameTvPop.setText(tvPopularData.getName());
            Glide.with(itemView).load(tvPopularData.getPosterPath())
                    .into(posterTvPop);
        }
    }
}
