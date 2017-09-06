package com.thebay.tb.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.thebay.tb.R;
import com.thebay.tb.dialog.CouponAddFragmentDialog;
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
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends AppCompatActivity {


    //    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @OnClick({R.id.fab})
    void click(View v) {
        switch (v.getId()) {
            case R.id.fab:
                FragmentManager fm = getSupportFragmentManager();
                CouponAddFragmentDialog dialogFragment = new CouponAddFragmentDialog();
                dialogFragment.show(fm, "coupon_add");
                break;
        }
}

    private Toolbar toolbar;
    private ArrayList<InquiryListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("쿠폰함");

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
        mTabLayout.addTab(mTabLayout.newTab().setText("사용가능한 쿠폰"));
        mTabLayout.addTab(mTabLayout.newTab().setText("지난 쿠폰내역"));
        mTabLayout.addTab(mTabLayout.newTab().setText("양도 쿠폰내역"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Creating TabPagerAdapter adapter
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mFab.show();
                } else if (position == 2) {
                    mFab.hide();
                } else {
                    mFab.hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

    public void popDateDialog(DatePickerDialog.OnDateSetListener dateSetListener) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
        if (Build.VERSION.SDK_INT >= 24) {
            // API 24 이상일 경우 시스템 기본 테마 사용
            context = this;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);
        datePickerDialog.show();

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
