package com.thebay.tb.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.thebay.tb.R;
import com.thebay.tb.event.FragmentReplaceEvent;
import com.thebay.tb.event.MessageEvent;
import com.thebay.tb.fragment.CouponAvailableListTabFragment;
import com.thebay.tb.fragment.CouponTransferListTabFragment;
import com.thebay.tb.fragment.CouponUnavailableListTabFragment;
import com.thebay.tb.model.InquiryListModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettlementActivity extends AppCompatActivity {


    //    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private Toolbar toolbar;
    private ArrayList<InquiryListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("결제");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTabLayout();
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);  //액티비티 애니메이션 없애기
        super.onResume();
    }

    public void setTabLayout() {

        // Initializing the TabLayout
        mTabLayout.addTab(mTabLayout.newTab().setText("결제"));
        mTabLayout.addTab(mTabLayout.newTab().setText("결제내역"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Creating TabPagerAdapter adapter
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // Set TabSelectedListener
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event instanceof FragmentReplaceEvent) {
            FragmentReplaceEvent fragmentReplaceEvent = (FragmentReplaceEvent) event;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragmentReplaceEvent.getFragment()).addToBackStack("null").commit();
            setTitle(fragmentReplaceEvent.getFragmentName());

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        // Count number of tabs
        private int tabCount;

        public PagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {

            // Returning the current tabs
            switch (position) {
                case 0:
                    CouponAvailableListTabFragment tabFragment1 = new CouponAvailableListTabFragment();
                    return tabFragment1;
                case 1:
                    CouponUnavailableListTabFragment tabFragment2 = new CouponUnavailableListTabFragment();
                    return tabFragment2;
                case 2:
                    CouponTransferListTabFragment tabFragment3 = new CouponTransferListTabFragment();
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
}
