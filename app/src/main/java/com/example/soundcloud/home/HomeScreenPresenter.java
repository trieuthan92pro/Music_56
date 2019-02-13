package com.example.soundcloud.home;

import com.example.soundcloud.R;
import com.example.soundcloud.data.TabInfo;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {
    private HomeScreenContract.View mView;

    public HomeScreenPresenter(){

    }

    public HomeScreenPresenter(HomeScreenContract.View view){
        mView = view;
    }

    @Override
    public TabInfo[] getTabsInfo() {
        TabInfo[] tabInfos = new TabInfo[] {
                new TabInfo(R.string.my_music_string, R.drawable.icon_my_music_deactive, R.drawable.icon_my_music_active),
                new TabInfo(R.string.discover_string, R.drawable.icon_discover_deactive, R.drawable.icon_discover_active),
                new TabInfo(R.string.favorite_string, R.drawable.icon_favorite_deactive, R.drawable.icon_favorite_active)
        };
        return tabInfos;
    }

    @Override
    public void start() {
        TabInfo[] tabInfos = getTabsInfo();
        mView.setTabIcon(tabInfos);
    }
}
