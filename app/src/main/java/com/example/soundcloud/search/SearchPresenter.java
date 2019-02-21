package com.example.soundcloud.search;

import com.example.soundcloud.data.model.SearchHistory;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SearchHistoryDataSource;
import com.example.soundcloud.data.source.SearchHistoryRepository;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongRepository;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {
    private static final int LIMIT = 50;
    private static final String MSG_SAVED = "Saved data success";
    private static final String MSG_CLEARED = "Clear data success!";
    private SearchHistoryRepository mSearchHistoryRepository;
    private SearchContract.View mView;
    private SongRepository mSearchSongRepository;

    public SearchPresenter(SearchHistoryRepository searchHistoryRepository,
                           SearchContract.View view,
                           SongRepository searchSongRepository) {
        mSearchHistoryRepository = searchHistoryRepository;
        mView = view;
        mSearchSongRepository = searchSongRepository;
    }

    @Override
    public void loadHistorySearch() {
        mSearchHistoryRepository.getHistorySearchKeys(
                new SearchHistoryDataSource.HistorySearchCallback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onSuccess(List<SearchHistory> searchHistories) {
                        mView.showSearchHistory(searchHistories);
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        mView.showError(errMsg);
                    }
                });
    }

    @Override
    public void loadSearchResult(String searchKey) {
        mSearchSongRepository.getSearchSongs(searchKey, LIMIT,
                new SongDataSource.LoadSongCallback() {
                    @Override
                    public void onSongsLoaded(List<Song> songs) {
                        mView.showSearchResult(songs);
                    }

                    @Override
                    public void onDataNotAvailable(Exception e) {
                        mView.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void saveRecentSearch() {
        mSearchHistoryRepository.saveHistory(null,
                new SearchHistoryDataSource.HistorySearchCallback() {
                    @Override
                    public void onSuccess() {
                        mView.showSuccess(MSG_SAVED);
                    }

                    @Override
                    public void onSuccess(List<SearchHistory> searchHistories) {

                    }

                    @Override
                    public void onFailed(String errMsg) {
                        mView.showError(errMsg);
                    }
                });
    }

    @Override
    public void clearSearchHistory() {
        mSearchHistoryRepository.deleteAllHistory(
                new SearchHistoryDataSource.HistorySearchCallback() {
                    @Override
                    public void onSuccess() {
                        mView.showSuccess(MSG_CLEARED);
                    }

                    @Override
                    public void onSuccess(List<SearchHistory> searchHistories) {
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        mView.showError(errMsg);
                    }
                });
    }

    @Override
    public void start() {
        loadHistorySearch();
    }
}
