package com.sample.datagridview.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

/**
 * Created by sarahlensing on 6/27/13.
 */
public class SyncListView extends ListView {

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void commonSetup() {
        if (!isInEditMode()) {
            SyncListBus bus = SyncListBus.shared();
            bus.register(this);
        }

        this.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    }

    public SyncListView(Context context) {
        super(context);
        commonSetup();
    }

    public SyncListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        commonSetup();
    }

    public SyncListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        commonSetup();
    }

    @Subscribe
    public void handleMotionEvent(SyncListMotionEvent event) {
        if(event.touchEvent!=null){
            if(event.intercept){
                onInterceptTouchEvent(event.touchEvent,true);
            }else{
                onTouchEvent(event.touchEvent,true);
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean ret = super.onTouchEvent(ev);
        SyncListBus.shared().post(new SyncListMotionEvent(this, ev,false));
        return ret;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean ret = super.onInterceptTouchEvent(ev);
        SyncListBus.shared().post(new SyncListMotionEvent(this, ev,true));
        return ret;
    }

    public boolean onTouchEvent(MotionEvent ev,boolean superHandle) {
        boolean ret = super.onTouchEvent(ev);
        return ret;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev,boolean superHandle) {
        boolean ret = super.onInterceptTouchEvent(ev);
        return ret;
    }
}