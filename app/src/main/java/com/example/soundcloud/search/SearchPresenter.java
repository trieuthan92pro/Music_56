package com.example.soundcloud.search;

import com.example.soundcloud.data.model.History;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SearchHistoryDataSource;
import com.example.soundcloud.data.source.SearchHistoryRepository;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {
    private static final int LIMIT = 50;
    private static final int MAX = 6;
    private static final String MSG_SAVED = "Saved data success";
    private static final String MSG_CLEARED = "Clear data success!";
    private SearchHistoryRepository mHistoryRepository;
    private SearchContract.View mView;
    private SongRepository mSearchSongRepository;
    private List<History> mSearchHistories;
    private List<History> mRecentSearch;

    public SearchPresenter(SearchHistoryRepository searchHistoryRepository,
                           SearchContract.View view,
                           SongRepository searchSongRepository) {
        mHistoryRepository = searchHistoryRepository;
        mView = view;
        mSearchSongRepository = searchSongRepository;
        mRecentSearch = new ArrayList<>();
    }

    @Override
    public void loadHistorySearch() {
        mHistoryRepository.getHistories(
                new SearchHistoryDataSource.HistorySearchCallback() {
                    public void onSuccess(List<History> searchHistories) {
                        mSearchHistories = searchHistories;
                        int max = searchHistories.size() < MAX ? searchHistories.size() : 6;
                        mView.showSearchHistory(searchHistories.subList(0, max));
                    }

                    @Override
                    public void onFailed(Exception e) {
                        mView.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void loadSearchResult(String searchKey) {
        mSearchSongRepository.searchSong(searchKey, LIMIT,
                new SongDataSource.LoadSongCallback() {
                    @Override
                    public void onSongsLoaded(List<Song> songs) {
                        mView.showProgressBar(false);
                        mView.showSearchResult(songs);
                    }

                    @Override
                    public void onDataNotAvailable(Exception e) {
                        mView.showProgressBar(false);
                        mView.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void saveRecentSearch() {
        mHistoryRepository.saveHistories(mRecentSearch,
                new SearchHistoryDataSource.CallBack() {
                    @Override
                    public void onSuccess() {
                        mView.showSuccess(MSG_SAVED);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        mView.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void clearSearchHistory() {
        mHistoryRepository.clearHistories(
                new SearchHistoryDataSource.CallBack() {
                    @Override
                    public void onSuccess() {
                        mView.showSuccess(MSG_CLEARED);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        mView.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void start() {
        loadHistorySearch();
    }

    @Override
    public List<History> getSearchHistories() {
        return mSearchHistories;
    }

    @Override
    public void addSearchKey(History searchHistory) {
        mRecentSearch.add(searchHistory);
        mSearchHistories.add(searchHistory);
    }
}
