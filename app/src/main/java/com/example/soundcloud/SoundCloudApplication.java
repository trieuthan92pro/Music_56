package com.example.soundcloud;

import android.app.Application;

public class SoundCloudApplication extends Application {
    private static SoundCloudApplication sSoundCloudApplication;

    public static SoundCloudApplication getInstance(){
        return sSoundCloudApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSoundCloudApplication = this;
    }
}
