package com.example.soundcloud.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.soundcloud.data.TabInfo;
import com.example.soundcloud.discover.DiscoverFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private TabInfo[] mTabInfos;
    private HomeScreenContract.Presenter mPresenter;
    private Context context;

    public HomeFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        mPresenter = new HomeScreenPresenter();
        mTabInfos = mPresenter.getTabsInfo();
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
            return context.getString(tabInfo.getTabTextId());
    }
}
