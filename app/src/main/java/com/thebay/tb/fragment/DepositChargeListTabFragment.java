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
import com.thebay.tb.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.thebay.tb.lib.RecyclerViewUtils;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.model.DepositChargeListModel;
import com.thebay.tb.view.DepositChargeHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class DepositChargeListTabFragment extends Fragment implements Serializable {
//    private RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    CoordinatorLayout mParentLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

//    @OnClick({R.id.search_button})
//    void click(View v) {
//        switch (v.getId()) {
//            case R.id.search_button:
//
//                break;
//        }
//    }

    private Unbinder unbinder;

    public static DepositChargeListTabFragment newInstance() {
        DepositChargeListTabFragment fragment = new DepositChargeListTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_deposit_charge, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //init data
        ArrayList<DepositChargeListModel> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new DepositChargeListModel("2017-09-04", "2017-09-04", "김경애", "20000원", "신한은행 110-436-981683 (김성훈(더베이))", "취소"));
        }

        ListAdapter dataAdapter = new ListAdapter(getActivity());
        dataAdapter.setData(dataList);

        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(dataAdapter);
        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //add a HeaderView
        RecyclerViewUtils.setHeaderView(mRecyclerView, new DepositChargeHeader(getActivity()));

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
        private ArrayList<DepositChargeListModel> mDataList = new ArrayList<>();

        public ListAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<DepositChargeListModel> list) {
            this.mDataList = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_deposit_charge, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.requestDateText.setText(mDataList.get(position).getRequestDate());
            viewHolder.depositDateText.setText(mDataList.get(position).getDepositDate());
            viewHolder.nameText.setText(mDataList.get(position).getName());
            viewHolder.moneyText.setText(mDataList.get(position).getMoney());
            viewHolder.accountNumberText.setText(mDataList.get(position).getAccountNumber());
            viewHolder.stateText.setText(mDataList.get(position).getState());
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            private TextView requestDateText;
            private TextView depositDateText;
            private TextView nameText;
            private TextView moneyText;
            private TextView accountNumberText;
            private TextView stateText;

            public ViewHolder(View itemView) {
                super(itemView);

                requestDateText = (TextView) itemView.findViewById(R.id.request_date_text);
                depositDateText = (TextView) itemView.findViewById(R.id.deposit_date_text);
                nameText = (TextView) itemView.findViewById(R.id.name_text);
                moneyText = (TextView) itemView.findViewById(R.id.money_text);
                accountNumberText = (TextView) itemView.findViewById(R.id.account_number);
                stateText = (TextView) itemView.findViewById(R.id.state_text);

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
