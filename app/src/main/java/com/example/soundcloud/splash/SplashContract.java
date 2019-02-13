package com.example.soundcloud.splash;


import com.example.soundcloud.BasePresenter;

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
