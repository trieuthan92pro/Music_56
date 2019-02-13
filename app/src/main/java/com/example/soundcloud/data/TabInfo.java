package com.example.soundcloud.data;

public class TabInfo {
    private int mTextResource;
    private int mImageResource;

    public TabInfo() {
    }

    public TabInfo(int textResource, int imageResource) {
        mTextResource = textResource;
        mImageResource = imageResource;
    }

    public int getTabTextId() {
        return mTextResource;
    }

    public void setTabTextId(int tabTextId) {
        mTextResource = tabTextId;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }
}
