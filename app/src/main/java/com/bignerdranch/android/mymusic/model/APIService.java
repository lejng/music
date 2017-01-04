package com.bignerdranch.android.mymusic.model;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("http://cache-minsk01.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json")
    Call<List<Artist>> loadArtist();
}
