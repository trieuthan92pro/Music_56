package com.example.soundcloud.data.source;

import com.example.soundcloud.data.Song;

import java.util.List;

public interface SongDataSource {
    interface LoadSongCallback {

        void onSongsLoaded(List<Song> songs);

        void onDataNotAvailable();
    }

    interface GetSongCallback {

        void onSongLoaded(Song song);

        void onDataNotAvailable();
    }
}
