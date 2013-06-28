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

    MotionEvent touchEvent;
    int i;
    int i2;
    int i3;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void commonSetup() {
        if (!isInEditMode()) {
            SyncListBus bus = SyncListBus.shared();
            bus.register(this);
        }

        this.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                Log.d("d", "d");
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                SyncListView.this.i = i;
                SyncListView.this.i2 = i2;
                SyncListView.this.i3 = i3;
                SyncListBus.shared().post(new SyncListScrollEvent(SyncListView.this, i, i2, i3));
            }
        });
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
    public void handleScrollEvent(SyncListScrollEvent event) {
//        if (event.listView == null) {
//            return;
//        }
//        if (event.listView != this) {
//            View child = event.listView.getChildAt(event.i);
//            if (child == null) {
//                return;
//            }
//            int top = child.getTop();
//            if (event.listView != this) {
//                scrollTo(0, top);
//
//            }
//        }
    }

    @Subscribe
    public void handleMotionEvent(SyncListMotionEvent event) {
        if (event.listView == null) {
            return;
        }

        View child = event.listView.getChildAt(0);
        if (child == null) {
            return;
        }
        int top = child.getTop();
        if (event.listView != this) {
            scrollTo(0, -top);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean ret = super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                this.touchEvent = ev;
                SyncListBus.shared().post(new SyncListMotionEvent(this, touchEvent));
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.d("d", "d");
                break;
            default:
                Log.d("d", "d");
                break;
        }

        return ret;
    }


}