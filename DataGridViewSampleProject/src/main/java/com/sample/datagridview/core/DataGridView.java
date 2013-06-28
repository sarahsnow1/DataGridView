package com.sample.datagridview.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.sample.datagridview.R;

/**
 * Created by sarahlensing on 6/27/13.
 */
public abstract class DataGridView extends Fragment {

    public abstract int numberOfColumns();
    public abstract int numberOfRowsForColumn(int column);
    public abstract View viewForColumnRow(int column, int row, View convertView, View parent);
    public abstract FragmentManager fragmentManager();

    private ViewPager pager;
    private SyncListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.data_grid_view, null);
        pager = (ViewPager) v.findViewById(R.id.viewPager);
        listView = (SyncListView) v.findViewById(R.id.listView);
        setupPager();
        return v;
    }

    private void setupPager() {
        pager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int column) {
                PagerFragment fragment = new PagerFragment();
//                fragment.onPagerFragmentItemClickListener = new PagerFragment.OnPagerFragmentItemClickListener() {
//                    @Override
//                    public void onPagerItemClicked(View view) {
//                        if (onPagerFragmentItemClickListener != null) {
//                            onPagerFragmentItemClickListener.onPagerItemClicked(view);
//                        }
//                    }
//                };

                final int columnFnl = column;
                fragment.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return numberOfRowsForColumn(columnFnl);
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(int row, View convertView, ViewGroup parent) {
                        return viewForColumnRow(columnFnl, row, convertView, parent);
                    }
                });
                return fragment;
            }

            @Override
            public int getCount() {
                return numberOfColumns();
            }

        });
    }

}

