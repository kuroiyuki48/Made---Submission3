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
import com.example.submission3.model.TVShowData;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.CardViewViewHolder> {
    private final ArrayList<TVShowData> tvShowData = new ArrayList<>();

    public void setTVShowData(ArrayList<TVShowData> itemData) {
        tvShowData.clear();
        tvShowData.addAll(itemData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowAdapter.CardViewViewHolder cardViewViewHolder, int i) {
        cardViewViewHolder.bind(tvShowData.get(i));
    }

    @Override
    public int getItemCount() {
        return tvShowData.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView titleTv;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_tvairing);
            titleTv = itemView.findViewById(R.id.tv_item_tvair);
        }

        void bind(TVShowData tvShowData) {
            titleTv.setText(tvShowData.getName());
            Glide.with(itemView).load(tvShowData.getBackdropPath())
                    .into(imageView);
        }
    }
}
