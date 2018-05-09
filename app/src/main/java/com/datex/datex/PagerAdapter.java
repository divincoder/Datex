package com.datex.datex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.datex.datex.fragment.HbAlcTabFragment;
import com.datex.datex.fragment.LipidProfileFragment;
import com.datex.datex.fragment.PhysicalExamFragment;

/**
 * Created by Ofoegbu Valentine on 27/04/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    Bundle fragBundle = null;

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HbAlcTabFragment.newInstance(fragBundle);
            case 1:
                return LipidProfileFragment.newInstance(fragBundle);
            case 2:
                return PhysicalExamFragment.newInstance(fragBundle);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
