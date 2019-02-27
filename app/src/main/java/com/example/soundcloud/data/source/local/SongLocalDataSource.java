package com.example.soundcloud.data.source.local;

import android.content.ContentResolver;

import com.example.soundcloud.data.source.SongDataSource;

public class SongLocalDataSource implements SongDataSource.LocalDataSource {

    private ContentResolver mContentResolver;

    public SongLocalDataSource(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    @Override
    public void getSongs(SongDataSource.LoadSongCallback callback) {
        new LocalSongAsyncTask(mContentResolver, callback).execute();
    }
}
