package com.thebay.tb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.common.CommonLib;
import com.thebay.tb.lib.TaobaoRestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class LoginFragment extends Fragment implements Serializable {


    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;
    //    @BindView(R.id.edit_id_layout)
//    TextInputLayout mEditIdLayout;
//    @BindView(R.id.edit_id)
//    TextInputEditText mEditId;
//    @BindView(R.id.edit_pass_layout)
//    TextInputLayout mEditPassLayout;
//    @BindView(R.id.edit_pass)
//    TextInputEditText mEditPass;
    @BindView(R.id.auto_login_check_box)
    CheckBox mloginCheckBox;
//    @BindView(R.id.confirm_button)
//    Button mConfirmButton;
//    @BindView(R.id.search_id_button)
//    Button mSeachIdButton;
//    @BindView(R.id.search_pass_button)
//    Button mSearchPassButton;
//    @BindView(R.id.sign_up_button)
//    Button mSignUpButton;

    @OnClick({R.id.sign_up_button, R.id.confirm_button, R.id.search_id_button, R.id.search_pass_button})
    void click(View v) {
        switch (v.getId()) {
            case R.id.sign_up_button:
                //약관 확인
//                EventBus.getDefault().post(new FragmentReplaceEvent(ProvisionFragment.newInstance(), "약관 동의" ));
                CommonLib.subActivityIntent(getActivity(),ProvisionFragment.newInstance());
                break;

            case R.id.confirm_button:

                //로그인
                break;

            case R.id.search_id_button:
                CommonLib.subActivityIntent(getActivity(),SearchIdFragment.newInstance());
                //아이디 찾기
                break;

            case R.id.search_pass_button:
                CommonLib.subActivityIntent(getActivity(),SearchPasswordFragment.newInstance());
                //비밀번호 찾기
                break;

        }
    }

    private Unbinder unbinder;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("로그인");
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        setView();
    }

    private void setView() {

//        mSignUpButton.setOnClickListener(this);
//        mConfirmButton.setOnClickListener(this);
//        mSeachIdButton.setOnClickListener(this);
//        mSearchPassButton.setOnClickListener(this);

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


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.sign_up_button:
//                //약관 확인
//                EventBus.getDefault().post(new FragmentReplaceEvent(ProvisionFragment.newInstance(),false));
//                break;
//
//            case R.id.confirm_button:
    //로그인

//                ArrayList<LoginEditView> loginEditViews = new ArrayList<>();
//                CommonLib.getLoginEditView(mParentLayout, loginEditViews);
//
//                ArrayList<Integer> nullcheckViews = new ArrayList<>();
//                for (int i = 0; i < loginEditViews.size(); i++) {
//                    if (loginEditViews.get(i).checkValidate() != 0) {
//                        nullcheckViews.add(loginEditViews.get(i).checkValidate());
//
//                    }
//                }

//                for (int i = 0; i < nullcheckViews.size(); i++) {
//                    Log.d(TAG, "onClick: " + nullcheckViews.get(i).toString());
//                }

//                String idText = mEditId.getText().toString();
//                String passText = mEditPass.getText().toString();
//                if (TextUtils.isEmpty(idText) || TextUtils.isEmpty(passText)) {
//                    if(TextUtils.isEmpty(idText)){
//                        mEditIdLayout.setError("아이디를 입력해주세요.");
//                    }
//                    if(TextUtils.isEmpty(passText)){
//                        mEditPassLayout.setError("비밀번호를 입력해주세요.");
//                    }


//                } else if (TextUtils.isEmpty(idText)) {
//                    mEditPassLayout.setError("비밀번호를 입력해주세요.");
//                } else if (TextUtils.isEmpty(idText)) {
//                    mEditIdLayout.setError("아이디를 입력해주세요.");

    //로그인 통신
//                break;
//        }
//    }
}