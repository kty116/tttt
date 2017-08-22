package com.thebay.tb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thebay.tb.R;

/**
 * Created by kyoungae on 2017-08-21.
 */

public class DefaultCustomView extends LinearLayout {


    private LinearLayout layout;
    private ImageView img;
    private TextView text;
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
        inflate(getContext(), R.layout.view_custom, this);

        layout = (LinearLayout) findViewById(R.id.layout);
        img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
    }

    public String setKeyAndValue(){
        return typedArray.getString(R.styleable.CustomView_key)+" , "+text.getText().toString();
    }

    public boolean checkNullData(){
        return typedArray.getBoolean(R.styleable.CustomView_nullvalidata,false);
    }
}
