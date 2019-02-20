package com.example.soundcloud.data.model;

public class SearchHistory {
    private int mId;
    private String mSearchKey;

    public SearchHistory() {
    }

    public SearchHistory(int id, String searchKey) {
        mId = id;
        mSearchKey = searchKey;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getSearchKey() {
        return mSearchKey;
    }

    public void setSearchKey(String searchKey) {
        mSearchKey = searchKey;
    }
}
