package com.thebay.tb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.event.FragmentReplaceEvent;
import com.thebay.tb.lib.TaobaoRestClient;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class ProvisionFragment extends Fragment implements View.OnClickListener, Serializable {
//    private RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;
    @BindView(R.id.provision_check_box)
    CheckBox mProvisionCheckBox;
    @BindView(R.id.info_collection_check_box)
    CheckBox mInfoCollectionCheckBox;

    @BindView(R.id.cancel_button)
    Button mCancelButton;
    @BindView(R.id.confirm_button)
    Button mConfirmButton;
//    @OnClick({R.id.cancel_button, R.id.confirm_button})
//    public void changeFragment() {
//        EventBus.getDefault()
//    }

    private Unbinder unbinder;

    public static ProvisionFragment newInstance() {
        ProvisionFragment fragment = new ProvisionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "onCreateView 호출", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
        getActivity().setTitle("약관동의");
        View view = inflater.inflate(R.layout.fragment_provision, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setView();

    }

    private void setView() {
        mCancelButton.setOnClickListener(this);
        mConfirmButton.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_button:
                //로그인만 add되면 됨
//                getActivity().getSupportFragmentManager().popBackStack(); // activity의 finish 같은 역할
                Toast.makeText(getActivity(), "ddddddddd", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
                break;

            case R.id.confirm_button:
                //회원가입
                if(mInfoCollectionCheckBox.isChecked()&&mProvisionCheckBox.isChecked()){
                    EventBus.getDefault().post(new FragmentReplaceEvent(SignUpFragment.newInstance(),"회원가입"));
                }else {
                    Snackbar.make(mParentLayout, "모든 약관에 동의하셔야 합니다. ", Snackbar.LENGTH_LONG).show();
                }

                break;

        }
    }
}
