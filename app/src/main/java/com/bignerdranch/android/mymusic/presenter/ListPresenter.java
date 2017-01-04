package com.bignerdranch.android.mymusic.presenter;

import android.app.Activity;
import android.content.Intent;
import com.bignerdranch.android.mymusic.model.Artist;
import com.bignerdranch.android.mymusic.model.ListArtists;
import com.bignerdranch.android.mymusic.view.IView;
import com.bignerdranch.android.mymusic.view.ItemActivity;


public class ListPresenter implements IPresenter {
    private IView mView;
    private ListArtists mModel;


    public ListPresenter(IView view){
        mView = view;
        mModel = ListArtists.getInstance();
        mModel.setPresenter(this);
    }


    public void updateView() {
        mView.updateView();
    }

    @Override
    public void loadModel() {
        mModel.loadModel();
    }

    @Override
    public void startPageArtist(Artist artist, Activity activity) {
        Intent intent = ItemActivity.newIntent(activity,artist);
        activity.startActivity(intent);
    }

    @Override
    public int getListLength() {
        return mModel.getSize();
    }

    @Override
    public Artist getArtist(int position) {
        return mModel.getElement(position);
    }

    @Override
    public int getPosition(Artist artist) {
        return mModel.getPosition(artist);
    }
}
