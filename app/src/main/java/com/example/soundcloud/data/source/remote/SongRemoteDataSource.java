package com.example.soundcloud.data.source.remote;

import com.example.soundcloud.BuildConfig;
import com.example.soundcloud.data.model.DataHelper;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongDataSource.LoadSongCallback;

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {

    @Override
    public void getSongsByGenre(String genre, int limit, LoadSongCallback callBack) {
        getSongsDataFromAPI(genre, limit, callBack);
    }

    @Override
    public void getSearchSongs(String searchKey, int limit, LoadSongCallback callBack) {
        getSongsBySearchKey(searchKey, limit, callBack);
    }

    private void getSongsBySearchKey(String searchKey, int limit, LoadSongCallback callBack) {
        String url = DataHelper.SoundCloud.BASE_URL
                + DataHelper.SoundCloud.SEARCH
                + DataHelper.SoundCloud.QUERY_SEARCH
                + searchKey
                + DataHelper.SoundCloud.PARAM_CLIENT_ID
                + BuildConfig.API_KEY
                + DataHelper.SoundCloud.PARAM_LIMIT
                + limit;

        new SearchSongRemoteAsyncTask(callBack, searchKey).execute(url);
    }

    private void getSongsDataFromAPI(String genre, int limit, LoadSongCallback callBack) {
        String url = DataHelper.SoundCloud.BASE_URL
                + DataHelper.SoundCloud.PARAM_KIND
                + DataHelper.SoundCloud.PARAM_GENRE
                + DataHelper.SoundCloud.PARAM_TYPE
                + genre + DataHelper.SoundCloud.PARAM_CLIENT_ID
                + BuildConfig.API_KEY
                + DataHelper.SoundCloud.PARAM_LIMIT
                + limit;
        new RemoteDataHelperAsyncTask(callBack).execute(url);
    }
}
