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
import com.thebay.tb.model.PointListModel;
import com.thebay.tb.view.PointHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class PointFragment extends Fragment implements Serializable {
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

    public static PointFragment newInstance() {
        PointFragment fragment = new PointFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("포인트");
        View view = inflater.inflate(R.layout.fragment_point, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //init data
        ArrayList<PointListModel> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new PointListModel("구분", "+1000", "2017-09-05"));
        }

        PointAdapter dataAdapter = new PointAdapter(getActivity());
        dataAdapter.setData(dataList);

        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(dataAdapter);
        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //add a HeaderView
        RecyclerViewUtils.setHeaderView(mRecyclerView, new PointHeader(getActivity()));

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

    public class PointAdapter extends RecyclerView.Adapter {

        private LayoutInflater mLayoutInflater;
        private ArrayList<PointListModel> mDataList = new ArrayList<>();

        public PointAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<PointListModel> list) {
            this.mDataList = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_point, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.sectionText.setText(mDataList.get(position).getSection());
            viewHolder.pointText.setText(mDataList.get(position).getPoint());
            viewHolder.dateText.setText(mDataList.get(position).getDate());
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            private TextView sectionText;
            private TextView pointText;
            private TextView dateText;

            public ViewHolder(View itemView) {
                super(itemView);

                sectionText = (TextView) itemView.findViewById(R.id.section_text);
                pointText = (TextView) itemView.findViewById(R.id.point_text);
                dateText = (TextView) itemView.findViewById(R.id.date_text);

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
