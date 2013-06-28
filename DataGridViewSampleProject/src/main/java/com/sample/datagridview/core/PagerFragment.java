package com.sample.datagridview.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sample.datagridview.R;

/**
 * Created by sarahlensing on 6/27/13.
 */
public class PagerFragment extends Fragment {

    SyncListView listView;
    BaseAdapter listAdapter;

    public interface OnPagerFragmentItemClickListener {
        public void onPagerItemClicked(View view);
    }
    public OnPagerFragmentItemClickListener onPagerFragmentItemClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager, null);
        listView = (SyncListView) v.findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        return v;
    }

    public void setAdapter(BaseAdapter adapter) {
        listAdapter = adapter;
    }
}

