package com.thebay.tb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.thebay.tb.R;
import com.thebay.tb.event.FragmentReplaceEvent;
import com.thebay.tb.event.MessageEvent;
import com.thebay.tb.model.ActivityChangeIntentDataModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SubActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        if (getIntent() != null) {
            Intent intent = getIntent();

            ActivityChangeIntentDataModel dataModel = (ActivityChangeIntentDataModel) intent.getSerializableExtra("data");
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, dataModel.getFragment()).commit();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getWindow().setWindowAnimations();

//        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//        params.setScrollFlags(0);


        setView();

    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);  //액티비티 애니메이션 없애기
        super.onResume();
    }

    public void setView() {

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
//            if (fragmentReplaceEvent.isToolbarHide()) {
//                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//                Toast.makeText(this, "숨김", Toast.LENGTH_SHORT).show();
//            } else {
//                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//                params.setScrollFlags(0);
//                Toast.makeText(this, "보임", Toast.LENGTH_SHORT).show();
//            }
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
}
