package com.example.soundcloud.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.soundcloud.data.TabInfo;
import com.example.soundcloud.discover.DiscoverFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 3;
    private TabInfo[] mTabInfos;
    private Context mContext;

    public HomeFragmentPagerAdapter(Context context,
                                    FragmentManager fm,
                                    TabInfo[] tabInfos) {
        super(fm);
        mContext = context;
        mTabInfos = tabInfos;
    }

    @Override
    public Fragment getItem(int i) {
        return new DiscoverFragment();
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
