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
import com.example.submission3.adapter.TVShowAdapter;
import com.example.submission3.adapter.TVShowPopularAdapter;
import com.example.submission3.detail.DetailTVShowActivity;
import com.example.submission3.detail.DetailTVShowPopularActivity;
import com.example.submission3.model.TVShowData;
import com.example.submission3.model.TVShowPopularData;
import com.example.submission3.viewmodel.TVShowPopularViewModel;
import com.example.submission3.viewmodel.TVShowViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TVShowFragment extends Fragment {
    private TVShowAdapter tvShowAdapter;
    private TVShowPopularAdapter tvPopularAdapter;
    private RecyclerView rvTvPopular;
    private RecyclerView rvTvShow;
    private ProgressBar progressBar;
    private ProgressBar progressBarTvPop;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_tvshow_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TVShowViewModel tvShowViewModel;
        TVShowPopularViewModel tvPopularViewModel;

        rvTvShow = view.findViewById(R.id.rv_tv_airing);
        progressBar = view.findViewById(R.id.progressbar_tvair);
        progressBar.setVisibility(View.VISIBLE);

        rvTvPopular = view.findViewById(R.id.rv_tv_popular);
        progressBarTvPop = view.findViewById(R.id.progressbar_populartv);
        progressBarTvPop.setVisibility(View.VISIBLE);

        showRecyclerTvShow(view);
        showRecyclerTvPopular(view);

        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
        tvShowViewModel.setTvShow();
        tvShowViewModel.getTvShow().observe(this, getTvShow);

        tvPopularViewModel = ViewModelProviders.of(this).get(TVShowPopularViewModel.class);
        tvPopularViewModel.setTvPopular();
        tvPopularViewModel.getTvPopular().observe(this, getTvPopular);
    }

    private final Observer<ArrayList<TVShowData>> getTvShow = new Observer<ArrayList<TVShowData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TVShowData> tvShowData) {
            if (tvShowData != null) {
                tvShowAdapter.setTVShowData(tvShowData);
                progressBar.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvTvShow).setOnItemClickListener((rvTvShow, position, v) ->
                        showSelectedTvShow(tvShowData.get(position)));
            }
        }
    };

    private final Observer<ArrayList<TVShowPopularData>> getTvPopular = new Observer<ArrayList<TVShowPopularData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TVShowPopularData> tvPopularData) {
            if (tvPopularData != null) {
                tvPopularAdapter.setTVShowPopularData(tvPopularData);
                progressBarTvPop.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvTvPopular).setOnItemClickListener((rvTvPopular, position, v) ->
                        showSelectedTvPop(tvPopularData.get(position)));
            }
        }
    };

    private void showSelectedTvShow(TVShowData itemTvShow) {
        Intent intent = new Intent(getActivity(), DetailTVShowActivity.class);
        intent.putExtra(DetailTVShowActivity.EXTRA_TV, itemTvShow);
        startActivity(intent);
    }

    private void showSelectedTvPop(TVShowPopularData itemTvPop) {
        Intent mpInten = new Intent(getActivity(), DetailTVShowPopularActivity.class);
        mpInten.putExtra(DetailTVShowPopularActivity.EXTRA_TV_POP, itemTvPop);
        startActivity(mpInten);
    }

    private void showRecyclerTvShow(View view) {
        tvShowAdapter = new TVShowAdapter();
        tvShowAdapter.notifyDataSetChanged();
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, view.isInLayout()));
        rvTvShow.setAdapter(tvShowAdapter);
    }

    private void showRecyclerTvPopular(View view) {
        tvPopularAdapter = new TVShowPopularAdapter();
        tvPopularAdapter.notifyDataSetChanged();
        rvTvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvTvPopular.setAdapter(tvPopularAdapter);
    }
}
