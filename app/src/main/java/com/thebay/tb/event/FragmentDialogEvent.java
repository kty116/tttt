package com.thebay.tb.event;

import android.support.v4.app.DialogFragment;

/**
 * Created by kyoungae on 2017-08-29.
 */

public class FragmentDialogEvent implements MessageEvent {
    private DialogFragment dialogFragment;

    public FragmentDialogEvent(DialogFragment dialogFragment) {
        newInstance();
        this.dialogFragment = dialogFragment;
    }

    public static DialogFragment newInstance() {
        DialogFragment dialogFragment = new DialogFragment();
        return dialogFragment;
    }

    public DialogFragment getDialogFragment() {
        return dialogFragment;
    }

    public void setDialogFragment(DialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }
}
