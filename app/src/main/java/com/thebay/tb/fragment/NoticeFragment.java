package com.thebay.tb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.adapter.NoticeListAdapter;
import com.thebay.tb.common.CommonLib;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.listener.RecyclerViewOnItemClickListener;
import com.thebay.tb.model.NoticeListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;

public class NoticeFragment extends Fragment implements Serializable {
//    private RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    FrameLayout mParentLayout;
    @BindView(R.id.message_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.result_text)
    TextView mResultText;
//    @BindView(R.id.result_text)
//    TextView mResultText;

//    @OnClick({R.id.search_button})
//    void click(View v) {
//        switch (v.getId()) {
//            case R.id.search_button:
//
//                break;
//        }
//    }

    private Unbinder unbinder;
    private ArrayList<NoticeListModel> list;

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //리플레이스해도 호출 안됌 ui 작업 안됌
        //여기서 안바뀔 작업 데이터 호출 하기
        Log.d(TAG, "onCreate: ");
        list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int num = i + 1;
            list.add(new NoticeListModel("No." + num, new Date(System.currentTimeMillis()).toString(), "공지", "공지사항"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("공지사항");

        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated: ");

        //통신하고 LIST값 없으면 값없음
        if (list.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mResultText.setVisibility(View.VISIBLE);
        }
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        NoticeListAdapter adapter = new NoticeListAdapter(getActivity(), list);

        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewOnItemClickListener(getActivity(), mRecyclerView, new RecyclerViewOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
//                EventBus.getDefault().post(new FragmentReplaceEvent(MessageDetailsFragment.newInstance(),"상세보기"));
                CommonLib.subActivityIntent(getActivity(), NoticeDetailsFragment.newInstance());

            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "long click");
            }
        }));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //서치 버튼 있음
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String search_value = query;
                Toast.makeText(getActivity(), "" + query, Toast.LENGTH_SHORT).show();

                //리스트 다시 업데이트하고 값없으면 리스트 안보이게 하고 리절트 텍스트 보여주기
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_menu:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

}
