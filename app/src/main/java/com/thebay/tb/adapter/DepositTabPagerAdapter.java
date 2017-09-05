package com.thebay.tb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.thebay.tb.fragment.DepositChargeListTabFragment;
import com.thebay.tb.fragment.DepositRefundListTabFragment;
import com.thebay.tb.fragment.DepositUseListTabFragment;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class DepositTabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public DepositTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                DepositChargeListTabFragment tabFragment1 = new DepositChargeListTabFragment();
                return tabFragment1;
            case 1:
                DepositRefundListTabFragment tabFragment2 = new DepositRefundListTabFragment();
                return tabFragment2;
            case 2:
                DepositUseListTabFragment tabFragment3 = new DepositUseListTabFragment();
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
