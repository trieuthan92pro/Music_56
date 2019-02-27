package com.example.soundcloud.my_music;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongRepository;

import java.util.List;

public class MyMusicPresenter implements MyMusicContract.Presenter {
    private static String TITLE = "Downloaded";
    private MyMusicContract.View mView;
    private SongRepository mRepository;
    private Genre mGenre;

    public MyMusicPresenter(MyMusicContract.View view, SongRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void loadSongsLocal() {
        mRepository.getSongs(new SongDataSource.LoadSongCallback() {
            @Override
            public void onSongsLoaded(List<Song> songs) {
                mView.showLoading(false);
                mGenre = new Genre(TITLE, songs);
                mView.showRecyclerView(true);
                mView.putDataOnView(mGenre);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                mView.showError(e.getMessage());
            }
        });
    }

    @Override
    public void start() {
        loadSongsLocal();
    }

    @Override
    public Genre getGenre() {
        return mGenre;
    }
}
