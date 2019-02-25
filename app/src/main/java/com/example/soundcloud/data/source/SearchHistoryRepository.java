package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.History;

import java.util.List;

public class SearchHistoryRepository implements SearchHistoryDataSource.LocalDataSource {
    private SearchHistoryDataSource.LocalDataSource mLocalDataSource;

    public SearchHistoryRepository(SearchHistoryDataSource.LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void getHistories(String limit, SearchHistoryDataSource.HistorySearchCallback callBack) {
        mLocalDataSource.getHistories(limit, callBack);
    }

    @Override
    public void saveHistories(List<History> searchHistories,
                              SearchHistoryDataSource.CallBack callBack) {
        mLocalDataSource.saveHistories(searchHistories, callBack);
    }

    @Override
    public void clearHistories(SearchHistoryDataSource.CallBack callBack) {
        mLocalDataSource.clearHistories(callBack);
    }
}
