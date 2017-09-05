package com.thebay.tb.event;

import java.io.Serializable;

/**
 * Created by kyoungae on 2017-08-28.
 */

public class FragmentDataEvent implements MessageEvent {
    private Serializable data;

    public FragmentDataEvent(Serializable data) {
        this.data = data;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
}
