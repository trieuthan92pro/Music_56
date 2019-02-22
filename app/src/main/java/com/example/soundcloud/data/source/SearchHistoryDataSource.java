package com.example.soundcloud.data.source;

import com.example.soundcloud.data.model.History;

import java.util.List;

public interface SearchHistoryDataSource {
    interface HistorySearchCallback {
        void onSuccess(List<History> histories);

        void onFailed(Exception e);
    }

    interface CallBack {
        void onSuccess();

        void onFailed(Exception e);
    }

    interface LocalDataSource {
        void getHistories(HistorySearchCallback callBack);

        void saveHistories(List<History> searchHistories, CallBack callBack);

        void clearHistories(CallBack callBack);
    }
}
