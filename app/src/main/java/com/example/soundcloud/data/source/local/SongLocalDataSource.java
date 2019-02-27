package com.example.soundcloud.data.source.local;

import android.content.ContentResolver;

import com.example.soundcloud.data.source.SongDataSource;

public class SongLocalDataSource implements SongDataSource.LocalDataSource {
    private static SongLocalDataSource sInstance;
    private ContentResolver mContentResolver;

    private SongLocalDataSource() {

    }

    public SongLocalDataSource(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
        sInstance = this;
    }

    public static SongLocalDataSource getInstance() {
        return sInstance;
    }
    @Override
    public void getSongs(SongDataSource.LoadSongCallback callback) {
        new LocalSongAsyncTask(mContentResolver, callback).execute();
    }
}
