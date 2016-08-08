package edu.berkeley.datascience.contextualhealer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class IconTabsAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public IconTabsAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override // Returns the number of items to be displayed in ViewPager
    public int getCount() {
        return fragmentList.size();
    }

    @Override // Returns the Item at a particular position
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
}

