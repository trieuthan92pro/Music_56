package com.example.soundcloud.discover;

import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.GenreType;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SongDataSource;
import java.util.ArrayList;
import java.util.List;

public class DiscoverPresenter implements DiscoverContract.Presenter {
    private static final String ALL_MUSIC = "ALL MUSIC";
    private static final String ALL_AUDIO = "ALL AUDIO";
    private static final String ALTERNATIVE_ROCK = "ALTERNATIVE ROCK";
    private static final String AMBIENT = "AMBIENT";
    private static final String CLASSICAL = "CLASSICAL";
    private static final String COUNTRY = "COUNTRY";
    private static final String[] genreTitles = {
            ALL_MUSIC, ALL_AUDIO, ALTERNATIVE_ROCK, AMBIENT, CLASSICAL, COUNTRY
    };
    private static String[] genreAPIKeys = {
            GenreType.ALL_MUSIC, GenreType.ALL_AUDIO, GenreType.ALTERNATIVE_ROCK,
            GenreType.AMBIENT, GenreType.CLASSICAL, GenreType.COUNTRY
    };
    private DiscoverContract.View mView;
    private SongDataSource.RemoteDataSource mDataSource;
    private List<Genre> mList;

    public DiscoverPresenter(DiscoverContract.View view,
                             SongDataSource.RemoteDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
        mList = new ArrayList<>();
    }

    @Override
    public void start() {
        mView.showProgressbar(true);
        loadAllSong();
    }

    @Override
    public void loadAllSong() {
        for (int i = 0; i < genreTitles.length; i++) {
            String genreTitle = genreTitles[i];
            String genreKey = genreAPIKeys[i];
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    mDataSource.getSongByGenre(genreKey, 20, new SongDataSource.LoadSongCallback() {
                        @Override
                        public void onSongsLoaded(List<Song> songs) {
                            Genre genre = new Genre(genreTitle, songs);
                            mList.add(genre);
                            if (genreTitle.compareTo(COUNTRY) == 0) {
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
        mView.showSongList(mList);
    }
}
