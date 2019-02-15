package com.example.soundcloud.data.model;

public class TabInfo {
    private int mTextResource;
    private int mImageResource;
    private int mImageResourceActive;

    public TabInfo() {
    }

    public TabInfo(int textResource, int imageResource, int imageResourceActive) {
        mTextResource = textResource;
        mImageResource = imageResource;
        mImageResourceActive = imageResourceActive;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public int getTextResource() {
        return mTextResource;
    }

    public void setTextResource(int textResource) {
        mTextResource = textResource;
    }

    public int getImageResourceActive() {
        return mImageResourceActive;
    }

    public void setImageResourceActive(int imageResourceActive) {
        mImageResourceActive = imageResourceActive;
    }
}
