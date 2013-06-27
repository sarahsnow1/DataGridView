package com.sample.datagridview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.datagridview.lib.DataGridView;

public class MainActivity extends Activity {

    DataGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = new DataGridView(getBaseContext()) {

            String[][] data = {
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"},
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"},
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"},
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"},
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"},
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"},
                    {"one", "two", "three", "four", "five", "six", "seven", "eight"}
            };

            @Override
            public int numberOfColumns() {
                return data.length;
            }

            @Override
            public int numberOfRowsForColumn(int column) {
                return data[column].length;
            }

            @Override
            public View viewForColumnRow(int column, int row, View convertView, View parent) {
                if (convertView == null) {
                    convertView = new TextView(getBaseContext());
                    convertView.setLayoutParams(new AbsListView.LayoutParams(200, 100));
                    convertView.setMinimumWidth(200);
                }

                TextView textView = (TextView) convertView;
                textView.setText(data[row][column]);

                return convertView;
            }
        };

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(gridView, lp);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
