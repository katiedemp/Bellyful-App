package com.bellyful.bellyfulapp.CurrentJobsUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



import java.util.ArrayList;

public class CurrentJobViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

    private static int NUM_PAGES = 2;
    private final Bundle fragmentBundle;

    public CurrentJobViewPagerAdapter(@NonNull FragmentManager fm, Bundle data) {
        super(fm);
        fragmentBundle = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public void addFragment(Fragment fragment, String title){
        fragment.setArguments(fragmentBundle);
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }


}
