package com.sample.datagridview.core;

import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by sarahlensing on 6/27/13.
 */
public class SyncListMotionEvent {

    public ListView listView;
    public boolean intercept;
    public MotionEvent touchEvent;

    public SyncListMotionEvent(ListView listView, MotionEvent ev,boolean intercept) {
        super();
        this.listView = listView;
        this.touchEvent = ev;
        this.intercept = intercept;
    }
}
