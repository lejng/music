package com.bignerdranch.android.mymusic.model;






import android.util.Log;

import com.bignerdranch.android.mymusic.presenter.ListPresenter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListArtists {
    private List<Artist> mArtists;
    private ListPresenter mPresenter;
    private static ListArtists mInstance = new ListArtists();
    private final String TAG = "ListPresenter";



    private ListArtists(){
        mArtists = new ArrayList<>();
    }

    public static synchronized ListArtists getInstance(){
        return mInstance;
    }

    public void loadModel(){
        mArtists.clear();
        try {
            startRetrofit();
        }catch (Exception err){
            Log.e(TAG,"Cannot load json");
        }
    }

    public void setPresenter(ListPresenter presenter) {
        this.mPresenter = presenter;
    }

    public void updateView(){
        mPresenter.updateView();
    }


    public  Artist getElement(int i){
        return mArtists.get(i);
    }

    public int getSize(){
        return mArtists.size();
    }

    public int getPosition(Artist artist){
        return mArtists.indexOf(artist);
    }

    private void startRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cache-minsk01.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.js/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<Artist>> call = service.loadArtist();
        call.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                mArtists = response.body();
                updateView();
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {

            }
        });
    }
}
