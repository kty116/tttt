package com.thebay.tb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.adapter.InquiryListAdapter;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.model.InquiryListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class OrderFragment extends Fragment implements Serializable{
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
    private ArrayList<InquiryListModel> list;

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int num = i + 1;
            list.add(new InquiryListModel("No." + num+" /","재문의 /","2", new Date(System.currentTimeMillis()).toString(),"입고", "제목"));
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        getActivity().setTitle("주문내역");
//        unbinder = ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        InquiryListAdapter adapter = new InquiryListAdapter(getActivity(),list);

        mRecyclerView.setAdapter(adapter);
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

//        mParentLayout.addView(view);
//
//    }

}
