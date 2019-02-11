package com.example.soundcloud.splash_screen;


import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.BaseView;

public interface SplashContract {

    interface View {

        void showSoundCloudIcon(int iconId);

        void showAppName(int appNameId);

    }

    interface Presenter extends BasePresenter {
        int getIconId();
        int getAppName();
    }

}
