package com.example.soundcloud.home_screen;

import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.data.TabInfo;

public interface HomeScreenContract {
    interface View {
        void setTabIcon(TabInfo[] tabInfos);
    }

    interface Presenter extends BasePresenter{
        public TabInfo[] getTabsInfo();
    }
}
