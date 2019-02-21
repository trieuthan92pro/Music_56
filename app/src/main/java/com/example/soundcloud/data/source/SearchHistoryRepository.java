package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.SearchHistory;

import java.util.List;

public class SearchHistoryRepository implements SearchHistoryDataSource.LocalDataSource {
    private SearchHistoryDataSource.LocalDataSource mLocalDataSource;

    public SearchHistoryRepository(SearchHistoryDataSource.LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void getHistorySearchKeys(SearchHistoryDataSource.HistorySearchCallback callback) {
        mLocalDataSource.getHistorySearchKeys(callback);
    }

    @Override
    public void saveHistory(List<SearchHistory> searchHistories, SearchHistoryDataSource.HistorySearchCallback callback) {
        mLocalDataSource.saveHistory(searchHistories, callback);
    }

    @Override
    public void deleteAllHistory(SearchHistoryDataSource.HistorySearchCallback callback) {
        mLocalDataSource.deleteAllHistory(callback);
    }
}
