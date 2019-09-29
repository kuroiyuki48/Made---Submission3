package com.example.submission3.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.submission3.model.TVShowData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<TVShowData>> listTvShow = new MutableLiveData<>();

    public void setTvShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShowData> listTvShowItem = new ArrayList<>();
        String API_KEY = "c4c3528f8851067b6fa4ef6cf065a432";
        String url = "https://api.themoviedb.org/3/tv/airing_today?api_key=" + API_KEY + "&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listTv = responseObject.getJSONArray("results");

                    for (int i = 0; i < listTv.length(); i++) {
                        JSONObject tvshow = listTv.getJSONObject(i);
                        TVShowData tvShowData = new TVShowData(tvshow);
                        listTvShowItem.add(tvShowData);
                    }
                    listTvShow.postValue(listTvShowItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TVShowData>> getTvShow() {
        return listTvShow;
    }
}
