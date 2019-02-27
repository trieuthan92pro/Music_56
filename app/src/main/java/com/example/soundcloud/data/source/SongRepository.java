package com.example.soundcloud.data.source;

import com.example.soundcloud.data.source.local.SongLocalDataSource;
import com.example.soundcloud.data.source.remote.SongRemoteDataSource;

public class SongRepository implements SongDataSource.RemoteDataSource,
        SongDataSource.LocalDataSource {
    private SongRemoteDataSource mRemoteDataSource;
    private SongLocalDataSource mLocalDataSource;

    public SongRepository(SongRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public SongRepository(SongLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void getSongsByGenre(String genre, int limit, SongDataSource.LoadSongCallback callback) {
        mRemoteDataSource.getSongsByGenre(genre, limit, callback);
    }

    @Override
    public void searchSong(String searchKey, int limit,
                           SongDataSource.LoadSongCallback callback) {
        mRemoteDataSource.searchSong(searchKey, limit, callback);
    }

    @Override
    public void getSongs(SongDataSource.LoadSongCallback callback) {
        mLocalDataSource.getSongs(callback);
    }
}
