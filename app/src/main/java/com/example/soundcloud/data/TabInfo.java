package com.example.soundcloud.data;

public class TabInfo {
    private int mTabTextId;
    private int mTabIconId;

    public TabInfo() {
    }

    public TabInfo(int tabTextId, int tabIconId) {
        mTabTextId = tabTextId;
        mTabIconId = tabIconId;
    }

    public int getTabTextId() {
        return mTabTextId;
    }

    public void setTabTextId(int tabTextId) {
        mTabTextId = tabTextId;
    }

    public int getTabIconId() {
        return mTabIconId;
    }

    public void setTabIconId(int tabIconId) {
        mTabIconId = tabIconId;
    }
}
