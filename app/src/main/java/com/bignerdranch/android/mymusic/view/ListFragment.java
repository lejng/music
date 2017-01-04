package com.bignerdranch.android.mymusic.view;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bignerdranch.android.mymusic.R;
import com.bignerdranch.android.mymusic.model.Artist;
import com.bignerdranch.android.mymusic.presenter.IPresenter;
import com.bignerdranch.android.mymusic.presenter.ListPresenter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;


public class ListFragment extends Fragment implements IView {
    private RecyclerView mRecyclerView;
    private IPresenter mPresenter = new ListPresenter(this);
    private ArtistAdapter mAdapter;
    private ProgressBar mLoadIconMain;
    private static int position = 0;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAdapter = new ArtistAdapter();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.update_date) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mLoadIconMain.setVisibility(View.VISIBLE);
            mPresenter.loadModel();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.scrollToPosition(position);
        mAdapter.notifyDataSetChanged();
        mLoadIconMain.setVisibility(View.INVISIBLE);
        position = 0;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLoadIconMain = (ProgressBar)view.findViewById(R.id.load_icon);
        mLoadIconMain.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadModel();
        return view;
    }

    private class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImage;
        private TextView mTitle;
        private TextView mGenres;
        private TextView mInfo;
        private Artist mArtist;
        private ProgressBar mLoadIcon;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImage = (ImageView) itemView.findViewById(R.id.small_image);
            mGenres = (TextView) itemView.findViewById(R.id.genre);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mInfo = (TextView) itemView.findViewById(R.id.info);
            mLoadIcon = (ProgressBar) itemView.findViewById(R.id.load_icon);
        }

        public void bindArtist(Artist artist){
            mLoadIcon.setVisibility(View.VISIBLE);
            mArtist = artist;
            mTitle.setText(artist.getName());
            mGenres.setText(artist.getGenres());
            mInfo.setText(artist.getAlbums() + "  " + artist.getTracks());
            Log.d("URL","" + artist.getUrlSmallImage());
            Picasso.with(getActivity()).load(artist.getUrlSmallImage()).into(mImage,makeInvisibleProgressBar());
        }

        private Callback makeInvisibleProgressBar(){
            Callback callback = new Callback() {
                @Override
                public void onSuccess() {
                    mLoadIcon.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError() {
                   // mLoadIcon.setVisibility(View.INVISIBLE);
                }
            };
            return callback;
        }


        @Override
        public void onClick(View v) {
            position = mPresenter.getPosition(mArtist);
            mPresenter.startPageArtist(mArtist,getActivity());
        }
    }

    private class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder>{

        @Override
        public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ArtistViewHolder viewHolder = new ArtistViewHolder(getActivity().getLayoutInflater().inflate(R.layout.item_list,parent,false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ArtistViewHolder holder, int position) {
            Artist artist = mPresenter.getArtist(position);
            holder.bindArtist(artist);
        }

        @Override
        public int getItemCount() {
            return mPresenter.getListLength();
        }
    }

}
