package com.example.soundcloud.splash_screen;


public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mView;

    public SplashPresenter(SplashContract.View view){
        mView = view;
    }

    @Override
    public void start() {
        int appName = getAppName();
        int iconId = getIconId();
        mView.showAppName(appName);
        mView.showSoundCloudIcon(iconId);
    }

    @Override
    public int getIconId() {
        return 0;
    }

    @Override
    public int getAppName() {
        return 0;
    }
}
