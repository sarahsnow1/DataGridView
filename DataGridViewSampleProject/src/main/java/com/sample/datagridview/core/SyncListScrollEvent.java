package com.sample.datagridview.core;

import android.widget.ListView;

/**
 * Created by sarahlensing on 6/27/13.
 */
public class SyncListScrollEvent {

    public SyncListView listView;

    public int l;
    public int t;

    public SyncListScrollEvent(SyncListView listView, int inL, int inT) {
        super();
        this.listView = listView;
        this.l = inL;
        this.t = inT;
    }
}
