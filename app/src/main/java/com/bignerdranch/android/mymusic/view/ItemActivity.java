package com.bignerdranch.android.mymusic.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.android.mymusic.R;
import com.bignerdranch.android.mymusic.model.Artist;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ItemActivity extends AppCompatActivity {
    private Artist mArtist;
    private ProgressBar mLoadIcon;
    private static final String ARTIST = "com.bignerdranch.android.mymusic.view.artist";

    public static Intent newIntent(Activity activity,Artist artist){
        Intent intent = new Intent(activity, ItemActivity.class);
        intent.putExtra(ARTIST,artist);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtist = (Artist) getIntent().getSerializableExtra(ARTIST);
        setContentView(R.layout.activiti_item_list);
        mLoadIcon = (ProgressBar) findViewById(R.id.load_icon);
        setTitle(mArtist.getName());
        initViews();
    }

    private void initViews(){
        ImageView image = (ImageView) findViewById(R.id.big_image);
        Picasso.with(this).load(mArtist.getUrlBigImage()).into(image,new InvisibleBar());
        TextView tGenre = (TextView)findViewById(R.id.genre_activity);
        tGenre.setText(mArtist.getGenres());
        TextView tInfo = (TextView) findViewById(R.id.info_activity);
        tInfo.setText(mArtist.getAlbums() + "  " + mArtist.getTracks());
        TextView tDescription = (TextView) findViewById(R.id.description);
        tDescription.setText(mArtist.getDescription());

    }
    class InvisibleBar implements Callback {
        @Override
        public void onSuccess() {
            mLoadIcon.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError() {
            //mLoadIcon.setVisibility(View.INVISIBLE);

        }
    }
}
