package com.example.soundcloud.data.source.local;

import com.example.soundcloud.data.model.SearchHistory;
import com.example.soundcloud.data.source.SearchHistoryDataSource;

import java.util.List;

public class HistorySearchLocalDataSource implements SearchHistoryDataSource.LocalDataSource {
    private HistorySearchDatabaseHelper mDatabaseHelper;

    public HistorySearchLocalDataSource(HistorySearchDatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    @Override
    public void getHistorySearchKey(SearchHistoryDataSource.HistorySearchCallback callBack) {
        mDatabaseHelper.getAllSearchHistory(callBack);
    }

    @Override
    public void saveHistorySearchIntoDB(List<SearchHistory> searchHistories,
                                        SearchHistoryDataSource.HistorySearchCallback callBack) {
        mDatabaseHelper.addHistorySearch(searchHistories, callBack);
    }

    @Override
    public void deleteAllHistorySearchFromDB(SearchHistoryDataSource.HistorySearchCallback callBack) {
        mDatabaseHelper.deleteAllHistories(callBack);
    }

}
