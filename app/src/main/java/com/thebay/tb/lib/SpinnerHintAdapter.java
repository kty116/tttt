package com.thebay.tb.lib;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class SpinnerHintAdapter extends ArrayAdapter<String> {


    public SpinnerHintAdapter(Context context, int resource) {
        super(context, resource);
    }

    public SpinnerHintAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SpinnerHintAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    public SpinnerHintAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public SpinnerHintAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    public SpinnerHintAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
