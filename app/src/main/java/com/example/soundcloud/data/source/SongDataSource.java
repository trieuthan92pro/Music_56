package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.Song;

import java.util.List;

public interface SongDataSource {

    interface LoadSongCallback {
        void onSongsLoaded(List<Song> songs);

        void onDataNotAvailable(Exception e);
    }

    interface LocalDataSource {
        void getSongs(LoadSongCallback callback);
    }

    interface RemoteDataSource {
        void getSongsByGenre(String genre, int limit, LoadSongCallback callback);

        void searchSong(String searchKey, int limit, LoadSongCallback callback);
    }
}
