package com.example.soundcloud.splash_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soundcloud.R;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    private TextView mTextViewAppName;
    private ImageView mImageViewAppIcon;
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void showSoundCloudIcon(int iconId) {
        mImageViewAppIcon.setImageResource(iconId);
    }

    @Override
    public void showAppName(int appName) {
        mTextViewAppName.setText(getResources().getString(appName));
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
