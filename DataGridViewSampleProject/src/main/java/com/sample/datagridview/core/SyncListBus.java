package com.sample.datagridview.core;

import com.squareup.otto.Bus;

/**
 * Created by sarahlensing on 6/27/13.
 */
public class SyncListBus extends Bus {

    private static volatile SyncListBus instance;

    public static SyncListBus shared() {
        if (instance == null) {
            synchronized (SyncListBus.class) {
                if (instance == null) {
                    instance = new SyncListBus();
                }
            }
        }
        return instance;
    }

}

