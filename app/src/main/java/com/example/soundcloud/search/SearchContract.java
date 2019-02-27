package com.example.soundcloud.search;

import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.BaseView;
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.History;
import com.example.soundcloud.data.model.Song;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showProgressBar(boolean isLoading);

        void showSearchHistory(List<History> histories);

        void showSearchResult(List<Song> songs);

        void showError(String errMsg);

        void showSuccess(String msg);

        void showMoreHistory();
    }

    interface Presenter extends BasePresenter {
        void loadHistorySearch(String limit);

        void loadSearchResult(String searchKey);

        void saveRecentSearch();

        void clearSearchHistory();

        List<History> getSearchHistories();

        void addSearchKey(History searchHistory);

        void onQueryTextSubmit(String query);

        String getSearchKey();

        void setSearchKey(String searchKey);

        void addSearchKey(History searchHistory, boolean isAdding);

        Genre getGenre();

        void setAddSearchKey(boolean isAdding);
    }
}
