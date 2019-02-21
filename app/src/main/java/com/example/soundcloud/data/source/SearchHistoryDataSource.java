package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.SearchHistory;

import java.util.List;

public interface SearchHistoryDataSource {
    interface HistorySearchCallback {
        void onSuccess();

        void onSuccess(List<SearchHistory> histories);

        void onFailed(String errMsg);
    }

    interface LocalDataSource {
        void getHistorySearchKeys(HistorySearchCallback callBack);

        void saveHistory(List<SearchHistory> searchHistories, HistorySearchCallback callBack);

        void deleteAllHistory(HistorySearchCallback callBack);
    }
}
