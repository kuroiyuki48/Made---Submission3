package com.example.submission3.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission3.ItemClickSupport;
import com.example.submission3.R;
import com.example.submission3.adapter.MovieAdapter;
import com.example.submission3.adapter.MoviePopularAdapter;
import com.example.submission3.detail.DetailMovieActivity;
import com.example.submission3.detail.DetailMoviePopularActivity;
import com.example.submission3.model.MovieData;
import com.example.submission3.model.MoviePopularData;
import com.example.submission3.viewmodel.MoviePopularViewModel;
import com.example.submission3.viewmodel.MovieViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private MoviePopularAdapter moviePopularAdapter;
    private RecyclerView rvPopular;
    private RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private ProgressBar progressBarUp;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_movie_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MoviePopularViewModel moviePopularViewModel;
        MovieViewModel movieViewModel;

        rvPopular = view.findViewById(R.id.rv_popular);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        rvMovie = view.findViewById(R.id.rv_movie);
        progressBarUp = view.findViewById(R.id.progressbar_up);
        progressBarUp.setVisibility(View.VISIBLE);

        showRecycleMoviePopularData(view);
        showRecyclerMovieData(view);

        moviePopularViewModel = ViewModelProviders.of(this).get(MoviePopularViewModel.class);
        moviePopularViewModel.setMovie();
        moviePopularViewModel.getMovie().observe(this, getMoviePopularData);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.setMovie();
        movieViewModel.getMovie().observe(this, getMovieData);
    }

    private final Observer<ArrayList<MoviePopularData>> getMoviePopularData = new Observer<ArrayList<MoviePopularData>>() {
        @Override
        public void onChanged(ArrayList<MoviePopularData> moviePopularData) {
            if (moviePopularData != null) {
                moviePopularAdapter.setMoviePopularData(moviePopularData);
                progressBar.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvPopular).setOnItemClickListener((rvPopular, position, v) ->
                        showSelectedPopularData(moviePopularData.get(position)));
            }
        }
    };

    private final Observer<ArrayList<MovieData>> getMovieData = new Observer<ArrayList<MovieData>>() {
        @Override
        public void onChanged(ArrayList<MovieData> movieData) {
            if (movieData !=null) {
                movieAdapter.setMovieData(movieData);
                progressBarUp.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvMovie).setOnItemClickListener((rvMovie, position, v) ->
                        showSelectedData(movieData.get(position)));
            }
        }
    };

    private void showSelectedPopularData(MoviePopularData itemPopularData) {
        Intent pintent = new Intent(getActivity(), DetailMoviePopularActivity.class);
        pintent.putExtra(DetailMoviePopularActivity.EXTRA_POPULAR_MOVIE, itemPopularData);
        startActivity(pintent);
    }

    private void showSelectedData(MovieData itemData) {
        Intent Intent = new Intent(getActivity(), DetailMovieActivity.class);
        Intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, itemData);
        startActivity(Intent);
    }

    private void showRecycleMoviePopularData(View view) {
        moviePopularAdapter = new MoviePopularAdapter();
        moviePopularAdapter.notifyDataSetChanged();
        rvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, view.isInLayout()));
        rvPopular.setAdapter(moviePopularAdapter);
    }

    private void showRecyclerMovieData(View view) {
        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvMovie.setAdapter(movieAdapter);
    }
}
