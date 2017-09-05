package com.thebay.tb.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.thebay.tb.lib.RecyclerViewUtils;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.model.DepositUseListModel;
import com.thebay.tb.view.DepositUseHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class DepositUseListTabFragment extends Fragment implements Serializable{
//    private RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;

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

    public static DepositUseListTabFragment newInstance() {
        DepositUseListTabFragment fragment = new DepositUseListTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_deposit_use, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //init data
        ArrayList<DepositUseListModel> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new DepositUseListModel("사용일", "10000", "20000원", "컨텐츠 내용", "잔액 0원"));
        }

        DepositUseListAdapter dataAdapter = new DepositUseListAdapter(getActivity());
        dataAdapter.setData(dataList);

        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(dataAdapter);
        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //add a HeaderView
        RecyclerViewUtils.setHeaderView(mRecyclerView, new DepositUseHeader(getActivity()));

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

    public class DepositUseListAdapter extends RecyclerView.Adapter {

        private LayoutInflater mLayoutInflater;
        private ArrayList<DepositUseListModel> mDataList = new ArrayList<>();

        public DepositUseListAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<DepositUseListModel> list) {
            this.mDataList = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_deposit_use, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.useDateText.setText(mDataList.get(position).getUseDate());
            viewHolder.depositText.setText(mDataList.get(position).getDeposit());
            viewHolder.useText.setText(mDataList.get(position).getUse());
            viewHolder.contentText.setText(mDataList.get(position).getContent());
            viewHolder.balanceText.setText(mDataList.get(position).getBalance());
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            private TextView useDateText;
            private TextView depositText;
            private TextView useText;
            private TextView contentText;
            private TextView balanceText;

            public ViewHolder(View itemView) {
                super(itemView);

                useDateText = (TextView) itemView.findViewById(R.id.use_date_text);
                depositText = (TextView) itemView.findViewById(R.id.deposit_text);
                useText = (TextView) itemView.findViewById(R.id.use_text);
                contentText = (TextView) itemView.findViewById(R.id.content_text);
                balanceText = (TextView) itemView.findViewById(R.id.balance_text);

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
