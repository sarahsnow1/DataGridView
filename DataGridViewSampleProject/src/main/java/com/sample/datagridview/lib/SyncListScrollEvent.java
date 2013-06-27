package com.sample.datagridview.lib;

import android.widget.ListView;

/**
 * Created by sarahlensing on 6/27/13.
 */
public class SyncListScrollEvent {

    public ListView listView;

    public int i;
    public int i2;
    public int i3;

    public SyncListScrollEvent(ListView listView, int i, int i2, int i3) {
        super();
        this.listView = listView;
        this.i= i;
        this.i2= i2;
        this.i3= i3;
    }
}
