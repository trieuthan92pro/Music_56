package com.example.soundcloud.discover;

import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.GenreType;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscoverPresenter implements DiscoverContract.Presenter {
    private static final int LIMIT = 20;
    private static final String[] GENRES = {
            GenreType.ALL_MUSIC, GenreType.ALL_AUDIO, GenreType.ALTERNATIVE_ROCK,
            GenreType.AMBIENT, GenreType.CLASSICAL, GenreType.COUNTRY
    };
    private String[] mGenreTitles;
    private DiscoverContract.View mView;
    private SongRepository mSongRepository;
    private List<Genre> mGenres;

    public DiscoverPresenter(DiscoverContract.View view, SongRepository repository,
                             String[] genreTitles) {
        mView = view;
        mSongRepository = repository;
        mGenres = new ArrayList<>();
        mGenreTitles = genreTitles;
    }

    @Override
    public void start() {
        mView.showProgressbar(true);
        loadSongs();
    }

    @Override
    public void loadSongs() {
        for (int i = 0; i < mGenreTitles.length; i++) {
            String genreTitle = mGenreTitles[i];
            String genreKey = GENRES[i];
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    mSongRepository.getSongByGenre(genreKey, LIMIT, new SongDataSource.LoadSongCallback() {
                        @Override
                        public void onSongsLoaded(List<Song> songs) {
                            Genre genre = new Genre(genreTitle, songs);
                            mGenres.add(genre);
                            if (mGenres.size() == mGenreTitles.length) {
                                showData();
                            }
                        }

                        @Override
                        public void onDataNotAvailable(Exception e) {
                            mView.showErr(e.getMessage());
                        }
                    });
                }
            };
            runnable.run();
        }
    }

    private void showData() {
        mView.showProgressbar(false);
        mView.showSongList(mGenres);
    }
}
