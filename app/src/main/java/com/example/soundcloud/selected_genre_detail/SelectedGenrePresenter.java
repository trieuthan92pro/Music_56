package com.example.soundcloud.selected_genre_detail;

public class SelectedGenrePresenter implements SelectedGenreContract.Presenter {
    private SelectedGenreContract.View mView;

    public SelectedGenrePresenter(SelectedGenreContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.showData();
    }
}
