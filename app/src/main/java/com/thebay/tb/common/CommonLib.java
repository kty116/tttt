package com.thebay.tb.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.tb.activity.SubActivity;
import com.thebay.tb.lib.TaobaoRestClient;
import com.thebay.tb.model.ActivityChangeIntentDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kyoungae on 2017-08-21.
 */

public class CommonLib {

    public void getHttp() throws JSONException {

        RequestParams params = new RequestParams();
        params.put("key","value");

        TaobaoRestClient.get("나머지 주소",null,new JsonHttpResponseHandler() {
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

    public static void subActivityIntent(Activity activity, Fragment fragment){
        Intent intent = new Intent(activity, SubActivity.class);
        Bundle bundle = new Bundle();
        ActivityChangeIntentDataModel dataModel = new ActivityChangeIntentDataModel(fragment);
        bundle.putSerializable("data",dataModel);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
//
//    public static void toolbarHideActivityIntent(Activity activity, Fragment fragment){
//        Intent intent = new Intent(activity, OrderListActivity.class);
//        Bundle bundle = new Bundle();
//        ActivityChangeIntentDataModel dataModel = new ActivityChangeIntentDataModel(fragment);
//        bundle.putSerializable("data",dataModel);
//        intent.putExtras(bundle);
//        activity.startActivity(intent);
//    }

//    public static void getLoginEditView(ViewGroup viewGroup, ArrayList<LoginEditView> list) {
//
//        for (int i = 0; i < viewGroup.getChildCount(); i++) {
//            View view = viewGroup.getChildAt(i);
//
//            if (view instanceof ViewGroup) {  //뷰가 뷰그룹인경우
//                getLoginEditView((ViewGroup) view, list);
//            } else if (view instanceof LoginEditView) {
//                LoginEditView loginEditView = (LoginEditView) view;
//                list.add(loginEditView);
//            }
//        }
//    }



//    public static ArrayList getCustomView(ViewGroup viewGroup, View getView, ArrayList list) {
//
//        for (int i = 0; i < viewGroup.getChildCount(); i++) {
//            View view = viewGroup.getChildAt(i);
//
//            if (view instanceof ViewGroup) {  //뷰가 뷰그룹인경우
//                getCustomView((ViewGroup) view, getView, list);
//            } else if (view instanceof DefaultCustomView) {
//                DefaultCustomView defaultCustomView = (DefaultCustomView) view;
//                list.add(defaultCustomView);
//            }
//        }
//        return list;
//    }


}
