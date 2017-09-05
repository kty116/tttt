package com.thebay.tb.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.activity.MainActivity;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.view.CustomLoginEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;

public class SignUpFragment extends Fragment implements View.OnClickListener, Serializable {

    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;
    @BindView(R.id.confirm_button)
    Button mConfirmButton;
    @BindView(R.id.cancel_button)
    Button mCancelButton;

    private Unbinder unbinder;
    private boolean isNullCheck;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Toast.makeText(getActivity(), "onCreateView 호출", Toast.LENGTH_SHORT).show();

        getActivity().setTitle("회원가입");
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
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

    public void getLoginEditView(ViewGroup viewGroup, ArrayList<CustomLoginEditText> list) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if (view instanceof CustomLoginEditText) {  //뷰가 뷰그룹인경우
                CustomLoginEditText loginEditView = (CustomLoginEditText) view;
                boolean isEditViewNull = loginEditView.checkValidate();
                if(isEditViewNull){
                    isNullCheck = true;
                }
                list.add(loginEditView);

            } else if (view instanceof ViewGroup) {
                getLoginEditView((ViewGroup) view, list);

            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_button:
                ArrayList<CustomLoginEditText> loginEditViews = new ArrayList<>();
                getLoginEditView(mParentLayout, loginEditViews);

                Log.d(TAG, "onActivityCreated: " + loginEditViews.size());

                Log.d(TAG, "onClick: "+isNullCheck);

                if(isNullCheck){

                }else {
                    //서버에 값 전달하고 메인화면으로 가기

                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }

//                ArrayList<Integer> nullcheckViews = new ArrayList<>();
//                for (int i = 0; i < loginEditViews.size(); i++) {
//                    if (loginEditViews.get(i).checkValidate() != 0) {
//                        nullcheckViews.add(loginEditViews.get(i).checkValidate());
//                    }
//                }
//                Log.d(TAG, "onActivityCreated: " + nullcheckViews.size());

                break;

            case R.id.cancel_button:
                getActivity().getSupportFragmentManager().popBackStack();
//                EventBus.getDefault().post(new FragmentReplaceEvent(ProvisionFragment.newInstance()));
                break;
        }
    }
}
