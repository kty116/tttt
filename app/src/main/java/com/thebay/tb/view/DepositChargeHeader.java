package com.thebay.tb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.lib.TaobaoRestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class DepositChargeHeader extends RelativeLayout implements View.OnClickListener {

    public DepositChargeHeader(Context context) {
        super(context);
        init(context);
    }

    public DepositChargeHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DepositChargeHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.list_header_deposit_charge, this);

        Button confirmButton = (Button) findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //헤더의 입력된 데이터들 confirmButton 버튼 눌렀을때 서버랑 통신


        Toast.makeText(v.getContext(), "서버랑 통신", Toast.LENGTH_SHORT).show();
//        getHttp()로;

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
}
