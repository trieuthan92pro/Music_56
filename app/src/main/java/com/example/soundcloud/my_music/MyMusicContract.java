package com.example.soundcloud.my_music;

import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.BaseView;
import com.example.soundcloud.data.model.Genre;

public interface MyMusicContract {
    interface View extends BaseView<Presenter> {
        void putDataOnView(Genre genre);

        void showError(String message);

        void showLoading(boolean isLoading);

        void showRecyclerView(boolean isShowing);

        void showFolderImage(boolean isShowing);
    }

    interface Presenter extends BasePresenter {
        void loadSongsLocal();

        Genre getGenre();
    }
}
