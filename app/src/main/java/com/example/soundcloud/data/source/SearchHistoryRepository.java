package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.SearchHistory;

import java.util.List;

public class SearchHistoryRepository implements SearchHistoryDataSource.LocalDataSource {
    private SearchHistoryDataSource.LocalDataSource mLocalDataSource;

    public SearchHistoryRepository(SearchHistoryDataSource.LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void getHistorySearchKey(SearchHistoryDataSource.HistorySearchCallback callback) {
        mLocalDataSource.getHistorySearchKey(callback);
    }

    @Override
    public void saveHistorySearchIntoDB(List<SearchHistory> searchHistories, SearchHistoryDataSource.HistorySearchCallback callback) {
        mLocalDataSource.saveHistorySearchIntoDB(searchHistories, callback);
    }

    @Override
    public void deleteAllHistorySearchFromDB(SearchHistoryDataSource.HistorySearchCallback callback) {
        mLocalDataSource.deleteAllHistorySearchFromDB(callback);
    }
}
