package com.thebay.tb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.thebay.tb.R;
import com.thebay.tb.view.DefaultCustomView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

public class Blank2Fragment extends Fragment {

    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.id)
    DefaultCustomView idView;
    @BindView(R.id.pass)
    DefaultCustomView passView;

    private Unbinder unbinder;

    public Blank2Fragment() {
    }

    public static Blank2Fragment newInstance() {
        Blank2Fragment fragment = new Blank2Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String str = "";
        ArrayList<DefaultCustomView> defaultCustomViews = new ArrayList<>();

        getCustomView(layout, defaultCustomViews);

        Log.d("length", Integer.toString(defaultCustomViews.size()));

        for (int i = 0; i < defaultCustomViews.size(); i++) {
            if (!"".equals(str)) {
                str += "&";
            }
            str += defaultCustomViews.get(i).getKeyAndValue();
//            Log.d(TAG, "check: "+defaultCustomViews.get(i).checkValidate());
        }

        Log.d(TAG, "onActivityCreated: "+str);


//        try {
//            String s = "";
//            Class aClass = Class.forName("com.thebay.tb.view.DefaultCustomView");
//            Class[] cArg = new Class[1];
//            cArg[0] = String.class;
//            Method method = aClass.getMethod("setKeyAndValue");
//            Log.d(TAG, "onActivityCreated: " + method.invoke(DefaultCustomView.receiver));
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getCustomView(ViewGroup viewGroup, ArrayList<DefaultCustomView> list) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if (view instanceof ViewGroup) {  //뷰가 뷰그룹인경우
                getCustomView((ViewGroup) view, list);
            } else if (view instanceof DefaultCustomView) {
                DefaultCustomView defaultCustomView = (DefaultCustomView) view;
                list.add(defaultCustomView);
            }
        }
    }
}
