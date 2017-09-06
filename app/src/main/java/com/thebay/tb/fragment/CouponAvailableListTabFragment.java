package com.thebay.tb.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.model.CouponAvailableListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class CouponAvailableListTabFragment extends Fragment implements Serializable {
//    private RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    CoordinatorLayout mParentLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
//    @BindView(R.id.coupon_add_button)
//    FloatingActionButton mCouponAddButton;
//
//    @OnClick({R.id.coupon_add_button})
//    void click(View v) {
//        switch (v.getId()) {
//            case R.id.coupon_add_button:
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                CouponAddFragmentDialog dialogFragment = new CouponAddFragmentDialog();
//                dialogFragment.show(fm,"coupon_add");
//                break;
//        }
//    }

    private Unbinder unbinder;

    public static CouponAvailableListTabFragment newInstance() {
        CouponAvailableListTabFragment fragment = new CouponAvailableListTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_coupon_available, container, false);

        //fab 설정
//        FloatingActionButton couponAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
//        couponAddButton.setVisibility(View.VISIBLE);
//        couponAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                CouponAddFragmentDialog dialogFragment = new CouponAddFragmentDialog();
//                dialogFragment.show(fm,"coupon_add");
//            }
//        });

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //init data
        ArrayList<CouponAvailableListModel> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new CouponAvailableListModel("No", "할인쿠폰", "할인", "유효기간", "남은기간", "사용일", "상태"));
        }

        ListAdapter dataAdapter = new ListAdapter(getActivity());
        dataAdapter.setData(dataList);

//        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(dataAdapter);
        mRecyclerView.setAdapter(dataAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //add a HeaderView
//        RecyclerViewUtils.setHeaderView(mRecyclerView, new DepositChargeHeader(getActivity()));

        //add a FooterView
//        RecyclerViewUtils.setFooterView(mRecyclerView, new DepositChargeHeader(getActivity()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void getHttp(String relativeUrl, RequestParams params) throws JSONException {

//        RequestParams params = new RequestParams();
//        params.put("key","value");

        TaobaoRestClient.get(relativeUrl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // 받아온 JSONObject 자료 처리
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // 받아온 JSONArray 자료 처리
            }

            @Override
            public void onFinish() {
                // 끝나면 호출 되는 메소드
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // 통신 실패시 호출 되는 메소드
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class ListAdapter extends RecyclerView.Adapter {

        private LayoutInflater mLayoutInflater;
        private ArrayList<CouponAvailableListModel> mDataList = new ArrayList<>();

        public ListAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<CouponAvailableListModel> list) {
            this.mDataList = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_coupon_available, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.numberText.setText(mDataList.get(position).getNumber());
            viewHolder.couponNameText.setText(mDataList.get(position).getCouponName());
            viewHolder.discountText.setText(mDataList.get(position).getDiscount());
            viewHolder.validityText.setText(mDataList.get(position).getValidity());
            viewHolder.dateIssuedText.setText(mDataList.get(position).getDateIssued());
            viewHolder.remainingPeriodText.setText(mDataList.get(position).getRemainingPeriod());
            viewHolder.conditionText.setText(mDataList.get(position).getCondition());
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            private TextView numberText;
            private TextView couponNameText;
            private TextView discountText;
            private TextView validityText;
            private TextView dateIssuedText;
            private TextView remainingPeriodText;
            private TextView conditionText;

            public ViewHolder(View itemView) {
                super(itemView);

                numberText = (TextView) itemView.findViewById(R.id.number_text);
                couponNameText = (TextView) itemView.findViewById(R.id.coupon_name_text);
                discountText = (TextView) itemView.findViewById(R.id.discount_text);
                validityText = (TextView) itemView.findViewById(R.id.validity_text);
                dateIssuedText = (TextView) itemView.findViewById(R.id.date_issued_text);
                remainingPeriodText = (TextView) itemView.findViewById(R.id.remaining_period_text);
                conditionText = (TextView) itemView.findViewById(R.id.condition_text);

//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String text = mDataList.get(RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this));
//                        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        }
    }

}
