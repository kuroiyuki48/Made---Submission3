package com.example.submission3.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3.R;
import com.example.submission3.model.TVShowPopularData;

public class DetailTVShowPopularActivity extends AppCompatActivity {
    public static final String EXTRA_TV_POP = "extra_tv_pop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow_popular);
        TextView mvTitle = findViewById(R.id.tv_item_name);
        TextView releaseDate = findViewById(R.id.tv_item_release);
        TextView voteAverage = findViewById(R.id.tv_item_score);
        TextView tvPopularity = findViewById(R.id.tv_popularity);
        TextView contentOverview = findViewById(R.id.tv_item_overview);
        ImageView posterPath = findViewById(R.id.img_item_photo);

        TVShowPopularData tvpopularData = getIntent().getParcelableExtra(EXTRA_TV_POP);

        mvTitle.setText(tvpopularData.getName());
        releaseDate.setText(tvpopularData.getFirstAirDate());
        String voteNumber = tvpopularData.getVoteAverage().toString();
        String percent = "/10";
        String voteValue =voteNumber + percent;
        voteAverage.setText(voteValue);
        tvPopularity.setText(String.valueOf(tvpopularData.getPopularity()));
        contentOverview.setText(tvpopularData.getOverview());
        Glide.with(this).load(tvpopularData.getPosterPath())
                .into(posterPath);
    }
}

