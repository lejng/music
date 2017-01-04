package com.bignerdranch.android.mymusic.presenter;

import android.app.Activity;

import com.bignerdranch.android.mymusic.model.Artist;


public interface IPresenter {
    void loadModel();
    void startPageArtist(Artist artist, Activity activity);
    int getPosition(Artist artist);
    int getListLength();
    Artist getArtist(int position);
}
