package com.bignerdranch.android.mymusic.model;





import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Artist implements Serializable{

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("genres")
    @Expose
    private List<String> genres = new ArrayList<>();

    private String mGenres;

    @SerializedName("tracks")
    @Expose
    private String mTracks;

    @SerializedName("albums")
    @Expose
    private String mAlbums;

    @SerializedName("description")
    @Expose
    private String mDescription;


    @SerializedName("cover")
    @Expose
    private Cover cover;

    public Cover getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     * The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    /**
     * @return
     * The name
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName
     * The name
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return
     * The genres
     */
    public String getGenres() {
        String result = "";
        for(int i = 0; i < genres.size(); i++) {
            result += genres.get(i);
            if(i < genres.size() - 1)
                result += ", ";
        }
        return result;
    }

    public void setGenres(String mGenres) {
        this.mGenres = mGenres;
    }

    /**
     * @param genres
     * The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * @return
     * The albums
     */
    public String getAlbums() {
        return "альбомы: " + mAlbums;
    }

    /**
     * @param mAlbums
     * The albums
     */
    public void setAlbums(String mAlbums) {
        this.mAlbums =  mAlbums;
    }

    /**
     * @return
     * The tracks
     */
    public String getTracks() {
        return "треки: " + mTracks;
    }

    /**
     * @param mTracks
     * The tracks
     */
    public void setTracks(String mTracks) {
        this.mTracks =  mTracks;
    }

    /**
     * @return
     * The description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * @param mDescription
     * The description
     */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }


    public String getUrlSmallImage() {
        return cover.getSmall();
    }


    public String getUrlBigImage() {
        return cover.getBig();
    }

}
