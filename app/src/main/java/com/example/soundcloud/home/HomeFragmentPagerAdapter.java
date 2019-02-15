package com.example.soundcloud.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.soundcloud.data.model.TabInfo;
import com.example.soundcloud.discover.DiscoverFragment;
import com.example.soundcloud.favorite.FavoriteFragment;
import com.example.soundcloud.my_music.MyMusicFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 3;
    private static final int POSITION_MYMUSIC_TAB = 0;
    private static final int POSITION_DISCOVER_TAB = 1;
    private static final int POSITION_FAVORITE_TAB = 2;

    private TabInfo[] mTabInfos;
    private Context mContext;
    private Fragment mDiscoverFragment;
    private Fragment mMyMusicFragment;
    private Fragment mFavoriteFragment;

    public HomeFragmentPagerAdapter(Context context, FragmentManager fm, TabInfo[] tabInfos) {
        super(fm);
        mContext = context;
        mTabInfos = tabInfos;
        mDiscoverFragment = new DiscoverFragment();
        mMyMusicFragment = new MyMusicFragment();
        mFavoriteFragment = new FavoriteFragment();
    }

    @Override
    public Fragment getItem(int tabPosition) {
        switch (tabPosition) {
            case POSITION_MYMUSIC_TAB:
                return mMyMusicFragment;

            case POSITION_DISCOVER_TAB:
                return mDiscoverFragment;

            case POSITION_FAVORITE_TAB:
                return mFavoriteFragment;

            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        TabInfo tabInfo = mTabInfos[position];
        return mContext.getString(tabInfo.getTextResource());
    }
}
