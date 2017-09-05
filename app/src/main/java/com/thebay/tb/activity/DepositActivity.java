package com.thebay.tb.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.thebay.tb.R;
import com.thebay.tb.adapter.DepositTabPagerAdapter;
import com.thebay.tb.event.FragmentReplaceEvent;
import com.thebay.tb.event.MessageEvent;
import com.thebay.tb.model.InquiryListModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepositActivity extends AppCompatActivity {


//    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
//    @BindView(R.id.order_state_spinner)
//    Spinner mOrderStateSpinner;
//    @BindView(R.id.search_keyword_spinner)
//    Spinner mSearchKeywordSpinner;
//    @BindView(R.id.before_date)
//    TextView mBeforeDateText;
//    @BindView(R.id.after_date)
//    TextView mAfterDateText;

//    @OnClick({R.id.before_date, R.id.after_date})
//    void click(View v) {
//        switch (v.getId()) {
//            case R.id.before_date:
//                DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        int month = monthOfYear+1;
//                        mBeforeDateText.setText(year+"-"+month+"-"+dayOfMonth);
//                    }
//                };
//                popDateDialog(dateSetListener1);
//                //            Toast.makeText(OrderListActivity.this, ""+view.getId(), Toast.LENGTH_SHORT).show();
//
//                break;
//
//            case R.id.after_date:
//                DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        int month = monthOfYear+1;
//                        mAfterDateText.setText(year+"-"+month+"-"+dayOfMonth);
//                    }
//                };
//                popDateDialog(dateSetListener2);
//
//                break;
//        }
//    }

    //    private FrameLayout toolbarLayout;

    private Toolbar toolbar;
    private ArrayList<InquiryListModel> list;
//    private String[] yourArray = new String[]{"Staff", "Student", "Your Hint"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("예치금");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTabLayout();

//        list = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            int num = i + 1;
//            list.add(new InquiryListModel("No." + num + " /", "재문의 /", "2", new Date(System.currentTimeMillis()).toString(), "입고", "제목"));
//        }
//
//        InquiryListAdapter adapter = new InquiryListAdapter(this, list);
//
//        mRecyclerView.setAdapter(adapter);

//
//        SpinnerHintAdapter hintAdapter = new SpinnerHintAdapter(this, android.R.layout.simple_list_item_1, yourArray);
//        mOrderStateSpinner.setAdapter(hintAdapter);
//        // show hint
//        mOrderStateSpinner.setSelection(hintAdapter.getCount());

    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);  //액티비티 애니메이션 없애기
        super.onResume();
    }

    public void setTabLayout() {

        // Initializing the TabLayout
        mTabLayout.addTab(mTabLayout.newTab().setText("충전내역"));
        mTabLayout.addTab(mTabLayout.newTab().setText("환급내역"));
        mTabLayout.addTab(mTabLayout.newTab().setText("사용내역"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Creating TabPagerAdapter adapter
        DepositTabPagerAdapter pagerAdapter = new DepositTabPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
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

    public void popDateDialog(DatePickerDialog.OnDateSetListener dateSetListener){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
        if (Build.VERSION.SDK_INT >= 24) {
            // API 24 이상일 경우 시스템 기본 테마 사용
            context = this;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,dateSetListener , year, month, day);
        datePickerDialog.show();

    }

//    public DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            Toast.makeText(OrderListActivity.this, ""+year+"-"+monthOfYear+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
////            Toast.makeText(OrderListActivity.this, ""+view.getId(), Toast.LENGTH_SHORT).show();
//        }
//    };

//    public void addCustomImageInputView(String imgTitle, final int position) {
//        mFileInputLayout = (LinearLayout) view.findViewById(R.id.image_input_layout);
//        TextView fileText = (TextView) view.findViewById(R.id.file_name_text);
//        ImageButton fileRemoveButton = (ImageButton) view.findViewById(R.id.file_remove_button);
//        fileText.setText(imgTitle);
//
//        fileRemoveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mUploadFiles.remove(position);
//                mInputParent.removeView(view);
//            }
//        });

//        toolbarLayout.addView(view);

//    }
}
