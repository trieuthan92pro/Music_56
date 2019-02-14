package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.Song;

import java.util.List;

public interface SongDataSource {

    interface LoadSongCallback {
        void onSongsLoaded(List<Song> songs);

        void onDataNotAvailable(Exception e);
    }

    interface GetSongCallback {
        void onSongLoaded(Song song);

        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getSongByGenre(String genre, int limit, LoadSongCallback callback);

        void getSearchSong(String searchKey, int limit, LoadSongCallback callback);
    }
}
