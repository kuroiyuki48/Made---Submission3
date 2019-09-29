package com.example.submission3.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.submission3.model.TVShowPopularData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowPopularViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<TVShowPopularData>> listTvPopular = new MutableLiveData<>();

    public void setTvPopular() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShowPopularData> listTvPopularItem = new ArrayList<>();
        String API_KEY = "c4c3528f8851067b6fa4ef6cf065a432";
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY + "&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listTv = responseObject.getJSONArray("results");

                    for (int i = 0; i < listTv.length(); i++) {
                        JSONObject tvshow = listTv.getJSONObject(i);
                        TVShowPopularData tvPopularData = new TVShowPopularData(tvshow);
                        listTvPopularItem.add(tvPopularData);
                    }
                    listTvPopular.postValue(listTvPopularItem);
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

    public LiveData<ArrayList<TVShowPopularData>> getTvPopular() {
        return listTvPopular;
    }
}
