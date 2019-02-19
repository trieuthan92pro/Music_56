package com.example.soundcloud.discover;

import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.data.model.Genre;

import java.util.List;

public interface DiscoverContract {
    interface View {
        void showSongList(List<Genre> genres);

        void showErr(String message);

        void showProgressbar(boolean isShowLoading);
    }

    interface Presenter extends BasePresenter {
        void loadSongs();
    }
}
