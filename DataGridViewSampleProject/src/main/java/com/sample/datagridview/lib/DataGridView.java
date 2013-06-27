package com.sample.datagridview.lib;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

/**
 * Created by sarahlensing on 6/27/13.
 */
public abstract class DataGridView extends RelativeLayout {

    public abstract int numberOfColumns();
    public abstract int numberOfRowsForColumn(int column);
    public abstract View viewForColumnRow(int column, int row, View convertView, View parent);

    private ViewPager pager;
    private SyncListView listView;

    private int listViewWidth = 100;

    public DataGridView(Context context) {
        super(context);
        setup();
    }

    public DataGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public DataGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        setupListView();
        setupPager();
    }

    private void setupPager() {
        pager = new ViewPager(getContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(ALIGN_RIGHT, listView.getId());

        Fragment test = new Fragment();
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(test.getFragmentManager()) {
            @Override
            public Fragment getItem(int column) {
                PagerFragment fragment = new PagerFragment();
                fragment.getFragmentManager();
                fragment.onPagerFragmentItemClickListener = new PagerFragment.OnPagerFragmentItemClickListener() {
                    @Override
                    public void onPagerItemClicked(View view) {
//                        if (onPagerFragmentItemClickListener != null) {
//                            onPagerFragmentItemClickListener.onPagerItemClicked(view);
//                        }
                    }
                };
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

            @Override
            public CharSequence getPageTitle(int position) {
                return "title";
            }
        };

        pager.setAdapter(adapter);
        addView(pager, lp);
    }

    private void setupListView() {
        listView = new SyncListView(getContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(listViewWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(listView, lp);
    }
}

