package com.example.soundcloud.discover;

import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.GenreType;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SongDataSource;

import java.util.ArrayList;
import java.util.List;

public class DiscoverPresenter implements DiscoverContract.Presenter {
    private static String[] genreAPIKeys = {
            GenreType.ALL_MUSIC, GenreType.ALL_AUDIO, GenreType.ALTERNATIVE_ROCK,
            GenreType.AMBIENT, GenreType.CLASSICAL, GenreType.COUNTRY
    };
    private String[] mGenreTitles;
    private DiscoverContract.View mView;
    private SongDataSource.RemoteDataSource mDataSource;
    private List<Genre> mList;

    public DiscoverPresenter(DiscoverContract.View view, SongDataSource.RemoteDataSource dataSource,
                             String[] genreTitles) {
        mView = view;
        mDataSource = dataSource;
        mList = new ArrayList<>();
        mGenreTitles = genreTitles;
    }

    @Override
    public void start() {
        mView.showProgressbar(true);
        loadAllSong();
    }

    @Override
    public void loadAllSong() {
        for (int i = 0; i < mGenreTitles.length; i++) {
            String genreTitle = mGenreTitles[i];
            String genreKey = genreAPIKeys[i];
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    mDataSource.getSongByGenre(genreKey, 20, new SongDataSource.LoadSongCallback() {
                        @Override
                        public void onSongsLoaded(List<Song> songs) {
                            Genre genre = new Genre(genreTitle, songs);
                            mList.add(genre);
                            if(mList.size() == mGenreTitles.length){
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
