package com.yeqiu.hailhydra;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @project：map
 * @author：小卷子
 * @date 2019/3/19
 * @describe：
 * @fix：
 */
public class TestAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragments;

    public TestAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
