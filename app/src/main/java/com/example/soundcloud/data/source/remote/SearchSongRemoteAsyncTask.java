package com.example.soundcloud.data.source.remote;

import android.os.AsyncTask;

import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SongDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class SearchSongRemoteAsyncTask extends AsyncTask<String, Void, ArrayList<Song>> {
    private SongDataSource.LoadSongCallback mCallBack;
    private Exception mException;
    private String mSearchKey;

    SearchSongRemoteAsyncTask(SongDataSource.LoadSongCallback callBack, String searchKey) {
        mCallBack = callBack;
        mSearchKey = searchKey;
    }

    @Override
    protected ArrayList<Song> doInBackground(String... strings) {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            String json = SongLoaderUtils.getJSONFromAPI(strings[0]);
            songs = SongLoaderUtils.getSearchSong(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return songs;
    }

    @Override
    protected void onPostExecute(ArrayList<Song> songs) {
        super.onPostExecute(songs);
        if (mCallBack == null) {
            return;
        }
        if (mException == null) {
            mCallBack.onSongsLoaded(songs);
        } else {
            mCallBack.onDataNotAvailable(mException);
        }
    }
}
