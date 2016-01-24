package com.taro.tusk.Tools;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.taro.tusk.Tools.Current_Weather;
import com.taro.tusk.Tools.Weekly_Weather;

/**
 * Created by Tina on 15-08-29.
 */
public class WeatherFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Tab1", "Tab2" };
    private Context context;

    public WeatherFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                // Top Rated fragment activity
                return new Current_Weather();
            case 1:
                // Games fragment activity
                return new Weekly_Weather();
            //case 2:
                // Movies fragment activity
              //  return new Weekly_Weather();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}