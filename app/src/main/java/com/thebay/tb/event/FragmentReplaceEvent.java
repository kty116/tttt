package com.thebay.tb.event;

import android.support.v4.app.Fragment;

/**
 * Created by kyoungae on 2017-08-23.
 */

public class FragmentReplaceEvent implements MessageEvent {
    private Fragment fragment;
    private String fragmentName;
    private boolean isToolbarHide = false;

    public FragmentReplaceEvent(Fragment fragment, String fragmentName) {
        this.fragment = fragment;
        this.fragmentName = fragmentName;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}