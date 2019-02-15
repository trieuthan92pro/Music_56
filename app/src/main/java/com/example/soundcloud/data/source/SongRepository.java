package com.example.soundcloud.data.source;

import com.example.soundcloud.data.source.remote.SongRemoteDataSource;

public class SongRepository implements SongDataSource.RemoteDataSource{
    private SongRemoteDataSource mRemoteDataSource;

    public SongRepository(SongRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getSongByGenre(String genre, int limit, SongDataSource.LoadSongCallback callback) {
        mRemoteDataSource.getSongByGenre(genre, limit, callback);
    }

    @Override
    public void getSearchSong(String searchKey, int limit,
                              SongDataSource.LoadSongCallback callback) {
        mRemoteDataSource.getSearchSong(searchKey, limit, callback);
    }
}
