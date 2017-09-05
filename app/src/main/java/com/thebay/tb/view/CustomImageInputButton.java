package com.thebay.tb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.thebay.tb.R;

/**
 * Created by kyoungae on 2017-08-25.
 */

public class CustomImageInputButton extends LinearLayout implements View.OnFocusChangeListener {
    private LinearLayout bg;
    private TextInputLayout editLayout;
    private TextInputEditText editText;
    private String hint;
    private String name;
    //    public static final String ID_INPUT_VALUE = "textVisiblePassword";
//    public static final String PASSWORD_INPUT_VALUE = "textPassword";
//    public static final String EMAIL_INPUT_VALUE = "text";

    public CustomImageInputButton(Context context) {
        super(context);
//        initView();
    }

    public CustomImageInputButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomImageInputButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_login_edit_text, this);

        bg = (LinearLayout) findViewById(R.id.bg);
        editLayout = (TextInputLayout) findViewById(R.id.edit_layout);
        editText = (TextInputEditText) findViewById(R.id.edit_text);

        editText.setOnFocusChangeListener(this);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomLoginEditText);
        setTypeArray(typedArray);
    }

//    private void getAttrs(AttributeSet attrs) {
//
//    }
//
//    private void getAttrs(AttributeSet attrs, int defStyle) {
//        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton, defStyle, 0);
//        setTypeArray(typedArray);
//    }

    private void setTypeArray(TypedArray typedArray) {
//        int bg_resID = typedArray.getResourceId(R.styleable.LoginButton_bg, R.drawable.bottom_bt01);
//        bg.setBackgroundResource(bg_resID);
//        int symbol_resID = typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.bottom_bt01);
//        symbol.setImageResource(symbol_resID);


        hint = typedArray.getString(R.styleable.CustomLoginEditText_hint);
        editLayout.setHint(hint);

        name = typedArray.getString(R.styleable.CustomLoginEditText_name);
        switch (name) {
            case "id":
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case "password":
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                break;
        }

//        int textColor = typedArray.getColor(R.styleable., 0);
//        text.setTextColor(textColor);
//        String text_string = typedArray.getString(R.styleable.LoginButton_text);
//        editText.setText(text_string);
        typedArray.recycle();
    }

//    void setBg(int bg_resID) {
//        bg.setBackgroundResource(bg_resID);
//    }

//    void setTextColor(int color) {
//        text.setTextColor(color);
//    }
//
//    void setText(String text_string) {
//        text.setText(text_string);
//    }
//
//    void setText(int text_resID) {
//        text.setText(text_resID);
//    }

    public boolean checkValidate() {
        boolean isNullCheck = false;
        if (TextUtils.isEmpty(editText.getText().toString())) {  //빈 값일때
//            return 1;
            if (!(name.equals("recommendation_id") || name.equals("joining_path"))) {
                editLayout.setError(hint + "을(를) 입력해주세요.");
                isNullCheck = true;
            }
        }
        return isNullCheck;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            editLayout.setError(null);
            editLayout.setErrorEnabled(false);

        }
    }
}
