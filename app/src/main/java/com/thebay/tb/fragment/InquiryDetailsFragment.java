package com.thebay.tb.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
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
import cz.msebera.android.httpclient.util.TextUtils;

public class InquiryDetailsFragment extends Fragment implements Serializable {
//    private RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;
    @BindView(R.id.ripple_text)
    EditText mRippleText;
    @BindView(R.id.ripple_edit_scroll_view)
    ScrollView mRippleEditScrollView;
    @BindView(R.id.input_parent)
    LinearLayout mInputParent;
    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
//    @BindView(R.id.riffle_confirm_button)
//    Button mRiffleConfirmButton;

    @OnClick({R.id.riffle_confirm_button})
    void click(View v) {
        switch (v.getId()) {
            case R.id.riffle_confirm_button:
                if (TextUtils.isEmpty(mRippleText.getText().toString())) {
                    //edittext에 글이 없을때 버튼을 눌렀을때
                    Toast.makeText(getActivity(), "등록할 글이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    //서버로 데이터 전송하고 전송후에 데이터 다시 불러오기
                    addCustomImageInputView(mRippleText.getText().toString());
                    mScrollView.fullScroll(ScrollView.FOCUS_DOWN);

                }

                break;
        }
    }

    private Unbinder unbinder;

    public static InquiryDetailsFragment newInstance() {
        InquiryDetailsFragment fragment = new InquiryDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("1:1문의 상세");
        View view = inflater.inflate(R.layout.fragment_inquiry_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRippleEditScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRippleText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                return false;
            }
        });
        mRippleText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    mScrollView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mScrollView.smoothScrollBy(0, 800);
                        }
                    }, 100);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void addCustomImageInputView(String imgTitle) {
        //있는 댓글 수 만큼 뷰 늘리기
        final View view = LayoutInflater.from(mInputParent.getContext()).inflate(R.layout.view_item_ripple, mInputParent, false);
        LinearLayout mFileInputLayout = (LinearLayout) view.findViewById(R.id.image_input_layout);
        TextView fileText = (TextView) view.findViewById(R.id.content_text);
        TextView fileRemoveButton = (TextView) view.findViewById(R.id.delete_button);
        fileText.setText(imgTitle);

        fileRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관리자 리플은 삭제버튼 안뜨게
                mInputParent.removeView(view);
            }
        });

        mInputParent.addView(view);

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
