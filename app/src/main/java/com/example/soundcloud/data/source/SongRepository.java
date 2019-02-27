package com.example.soundcloud.data.source;

public class SongRepository implements SongDataSource.RemoteDataSource,
        SongDataSource.LocalDataSource {
    private static SongRepository sSongRepository;
    private SongDataSource.RemoteDataSource mRemoteDataSource;
    private SongDataSource.LocalDataSource mLocalDataSource;

    private SongRepository(){

    }

    private SongRepository(SongDataSource.RemoteDataSource remoteDataSource,
                          SongDataSource.LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static SongRepository getInstance(SongDataSource.RemoteDataSource remoteDataSource,
                                             SongDataSource.LocalDataSource localDataSource) {
        if(sSongRepository == null) {
            sSongRepository = new SongRepository(remoteDataSource, localDataSource);
        }
        return sSongRepository;
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
