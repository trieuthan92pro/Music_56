package com.example.soundcloud.data.model;

public class History {
    private int mId;
    private String mSearchKey;

    public History() {
    }

    public History(String searchKey) {
        mSearchKey = searchKey;
    }

    public History(int id, String searchKey) {
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
