package com.example.soundcloud.search;

import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.BaseView;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showProgressBar(boolean isShowing);

        void showHistorySearch();

        void showSearchResult();

        void showError(String errMsg);
    }

    interface Presenter extends BasePresenter {
        void loadHistorySearch();

        void loadSearchResult();

        void saveRecentSearch();
    }
}
