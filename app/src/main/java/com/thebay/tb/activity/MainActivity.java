package com.thebay.tb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thebay.tb.R;
import com.thebay.tb.common.CommonLib;
import com.thebay.tb.event.FragmentDialogEvent;
import com.thebay.tb.event.FragmentReplaceEvent;
import com.thebay.tb.event.MessageEvent;
import com.thebay.tb.fragment.InquiryFragment;
import com.thebay.tb.fragment.LoginFragment;
import com.thebay.tb.fragment.MainFragment;
import com.thebay.tb.fragment.MessageFragment;
import com.thebay.tb.fragment.NoticeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView mNav;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.user_id_text)
    TextView mUserIdText;
    @BindView(R.id.user_grade_text)
    TextView mUserGradeText;
    @BindView(R.id.deposit_text)
    TextView mDepositText;
    @BindView(R.id.settlement_count_text)
    TextView mSettlementCountText;
    @BindView(R.id.coupon_possession_text)
    TextView mCouponPossessionText;

    @OnClick({R.id.login_button, R.id.deposit_button, R.id.buy_count_button, R.id.coupon_button})
    void click(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, LoginFragment.newInstance()).commit();
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.deposit_button:
                startActivity(new Intent(this, DepositActivity.class));
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.buy_count_button:
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.coupon_button:
                mDrawer.closeDrawer(GravityCompat.START);
                break;

        }
    }

    private int checkedMenuItem;
    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, MainFragment.newInstance()).commit();

        setView();

    }

    public void setView() {
        mNav.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
////                onBackPressed();
//                Toast.makeText(this, "dddddddddddddd", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event instanceof FragmentReplaceEvent) {
            FragmentReplaceEvent fragmentReplaceEvent = (FragmentReplaceEvent) event;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragmentReplaceEvent.getFragment()).addToBackStack("null").commit();
        } else if (event instanceof FragmentDialogEvent) {
            FragmentDialogEvent fragmentDialogEvent = (FragmentDialogEvent) event;
            fragmentDialogEvent.getDialogFragment().show(getSupportFragmentManager(), "다이얼로그");
        }

    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);  //액티비티 애니메이션 없애기
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mNav.getMenu().findItem(checkedMenuItem).setChecked(false);
        checkedMenuItem = item.getItemId();
        mNav.getMenu().findItem(checkedMenuItem).setChecked(true);

        switch (item.getItemId()) {

            case R.id.info:
                //나의 정보

                break;

            case R.id.order_list:
                //주문내역

                startActivity(new Intent(this, OrderListActivity.class));
                break;

            case R.id.message:
                //쪽지함
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, MessageFragment.newInstance()).commit();
                CommonLib.subActivityIntent(this, MessageFragment.newInstance());

                break;

            case R.id.coupon:
                //쿠폰함

                break;

            case R.id.notice:
                //공지
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, NoticeFragment.newInstance()).commit();
                CommonLib.subActivityIntent(this,NoticeFragment.newInstance());
                break;

            case R.id.inquiry:
                //1:1문의

//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, InquiryFragment.newInstance()).commit();
                CommonLib.subActivityIntent(this,InquiryFragment.newInstance());
                break;

            case R.id.setting:
                //설정

                break;

        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
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

}
