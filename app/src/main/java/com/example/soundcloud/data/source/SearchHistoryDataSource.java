package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.SearchHistory;

import java.util.List;

public interface SearchHistoryDataSource {
    interface HistorySearchCallback {
        void onSuccess();

        void onSuccess(List<SearchHistory> searchHistories);

        void onFailed(String errMsg);
    }

    interface LocalDataSource {
        void getHistorySearchKey(HistorySearchCallback callback);

        void saveHistorySearchIntoDB(List<SearchHistory> searchHistories, HistorySearchCallback callback);

        void deleteAllHistorySearchFromDB(HistorySearchCallback callback);
    }
}
