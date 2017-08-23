package com.thebay.tb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thebay.tb.R;

/**
 * Created by kyoungae on 2017-08-21.
 */

public class DefaultCustomView extends View {


    private LinearLayout layout;
    private ImageView img;
    private EditText editText;
    private TypedArray typedArray;

    public DefaultCustomView(Context context) {
        super(context);
    }

    public DefaultCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public DefaultCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(getContext(), R.layout.view_custom,layout);

        layout = (LinearLayout) findViewById(R.id.layout);
        img = (ImageView) findViewById(R.id.img);
        editText = (EditText) findViewById(R.id.text);

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
    }

    public String getKeyAndValue(){
        return typedArray.getString(R.styleable.CustomView_key);
    }
//
//    public boolean checkValidate(){
//        boolean isNullCheck = false;
//        String editTextValue = editText.getText().toString();
//        if(!TextUtils.isEmpty(editTextValue)){
//            isNullCheck = true;
//        }
//        return isNullCheck;
//    }
}
