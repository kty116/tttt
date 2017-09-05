package com.thebay.tb.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.R;
import com.thebay.tb.lib.TaobaoRestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class DepositUseHeader extends RelativeLayout implements View.OnClickListener {

    private TextView mBeforeDateText;
    private TextView mAfterDateText;
    private Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH)+1;
    private int day = calendar.get(Calendar.DAY_OF_MONTH);

    public DepositUseHeader(Context context) {
        super(context);
        init(context);
    }

    public DepositUseHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DepositUseHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.list_header_deposit_use, this);

        Button searchButton = (Button) findViewById(R.id.search_button);
        mBeforeDateText = (TextView) findViewById(R.id.before_date_text);
        mAfterDateText = (TextView) findViewById(R.id.after_date_text);
        mBeforeDateText.setText(year+"-"+month+"-"+day);
        mAfterDateText.setText(year+"-"+month+"-"+day);

        searchButton.setOnClickListener(this);
        mBeforeDateText.setOnClickListener(this);
        mAfterDateText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //헤더의 입력된 데이터들 confirmButton 버튼 눌렀을때 서버랑 통신

        switch (v.getId()){
            case R.id.search_button:
                Toast.makeText(v.getContext(), "서버랑 통신", Toast.LENGTH_SHORT).show();

                break;

            case R.id.before_date_text:
                DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear+1;
                        mBeforeDateText.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                };
                popDateDialog(dateSetListener1);

                break;

            case R.id.after_date_text:
                DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear+1;
                        mAfterDateText.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                };
                popDateDialog(dateSetListener2);

                break;
        }



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

    public void popDateDialog(DatePickerDialog.OnDateSetListener dateSetListener){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(getContext(), android.R.style.Theme_Holo_Light_Dialog);
        if (Build.VERSION.SDK_INT >= 24) {
            // API 24 이상일 경우 시스템 기본 테마 사용
            context = getContext();
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,dateSetListener , year, month, day);
        datePickerDialog.show();

    }
}
