package com.example.soundcloud.data.source.remote;

import com.example.soundcloud.data.model.DataHelper;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongDataSource.LoadSongCallback;

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {

    @Override
    public void getSongByGenre(String genre, int limit, LoadSongCallback callback) {
        getSongDataFromAPI(genre, limit, callback);
    }

    @Override
    public void getSearchSong(String searchKey, int limit, LoadSongCallback callback) {

    }

    private void getSongDataFromAPI(String genre, int limit, LoadSongCallback callBack) {
        String url = DataHelper.SoundCloud.BASE_URL
                + DataHelper.SoundCloud.PARAM_KIND
                + DataHelper.SoundCloud.PARAM_GENRE
                + DataHelper.SoundCloud.PARAM_TYPE
                + genre + DataHelper.SoundCloud.PARAM_CLIENT_ID
                + DataHelper.API_KEY
                + DataHelper.SoundCloud.PARAM_LIMIT
                + limit;
        new GetRemoteDataAsyncTask(callBack).execute(url);
    }
}
