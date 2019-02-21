package com.example.soundcloud.data.source;

import com.example.soundcloud.data.source.remote.SongRemoteDataSource;

public class SongRepository implements SongDataSource.RemoteDataSource{
    private SongRemoteDataSource mRemoteDataSource;

    public SongRepository(SongRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getSongsByGenre(String genre, int limit, SongDataSource.LoadSongCallback callback) {
        mRemoteDataSource.getSongsByGenre(genre, limit, callback);
    }

    @Override
    public void getSearchSongs(String searchKey, int limit,
                               SongDataSource.LoadSongCallback callback) {
        mRemoteDataSource.getSearchSongs(searchKey, limit, callback);
    }
}
