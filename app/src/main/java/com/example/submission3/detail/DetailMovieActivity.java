package com.example.submission3.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3.R;
import com.example.submission3.model.MovieData;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        TextView mvTitle = findViewById(R.id.tv_item_name);
        TextView releaseDate = findViewById(R.id.tv_item_release);
        TextView voteAverage = findViewById(R.id.tv_item_score);
        TextView tvPopularity = findViewById(R.id.tv_popularity);
        TextView contentOverview = findViewById(R.id.tv_item_overview);
        ImageView posterPath = findViewById(R.id.img_item_photo);

        MovieData movieData = getIntent().getParcelableExtra(EXTRA_MOVIE);

        mvTitle.setText(movieData.getTitle());
        releaseDate.setText(movieData.getReleaseDate());
        String voteNumber = movieData.getVoteAverage().toString();
        String percent = "/10";
        String voteValue =voteNumber + percent;
        voteAverage.setText(voteValue);
        tvPopularity.setText(String.valueOf(movieData.getPopularity()));
        contentOverview.setText(movieData.getOverview());
        Glide.with(this).load(movieData.getPosterPath())
                .into(posterPath);
    }
}

