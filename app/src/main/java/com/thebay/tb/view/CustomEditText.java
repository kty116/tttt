package com.thebay.tb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.thebay.tb.R;

/**
 * Created by kyoungae on 2017-08-25.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    private TypedArray typedArray;

    public CustomEditText(Context context) {
        super(context);
//        initView();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditText);
    }

    public String getName(){
        return typedArray.getString(R.styleable.CustomEditText_key);
    }

//    private void setTypeArray(TypedArray typedArray) {

//        hint = typedArray.getString(R.styleable.CustomLoginEditText_hint);
//        editLayout.setHint(hint);
//
//        name = typedArray.getString(R.styleable.CustomLoginEditText_name);
//        switch (name) {
//            case "id":
//                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                break;
//            case "password":
//                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
//                break;
//        }

//        typedArray.recycle();
//    }

//    public boolean checkValidate() {
//        boolean isNullCheck = false;
//        if (TextUtils.isEmpty(editText.getText().toString())) {  //빈 값일때
////            return 1;
//            if (!(name.equals("recommendation_id") || name.equals("joining_path"))) {
//                editLayout.setError(hint + "을(를) 입력해주세요.");
//                isNullCheck = true;
//            }
//        }
//        return isNullCheck;
//    }

//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if(hasFocus){
//            editLayout.setError(null);
//            editLayout.setErrorEnabled(false);
//
//        }
//    }
}
