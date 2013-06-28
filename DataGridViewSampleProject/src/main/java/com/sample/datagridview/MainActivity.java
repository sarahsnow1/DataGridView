package com.sample.datagridview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.sample.datagridview.core.DataGridView;

public class MainActivity extends FragmentActivity {

    DataGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                final String[][] data = {
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight"}
        };
        DataGridView fragment = new DataGridView() {

            @Override
            public int numberOfListItems() {
                return data[0].length;
            }

            @Override
            public View viewForListRow(int row, View convertView, View parent) {
                if (convertView == null) {
                    convertView = new TextView(getBaseContext());
                    convertView.setLayoutParams(new AbsListView.LayoutParams(100, 100));
                    convertView.setMinimumWidth(100);
                }

                TextView textView = (TextView) convertView;
                textView.setText(String.valueOf(row));

                return convertView;
            }

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
                textView.setText(data[column][row]);

                return convertView;
            }

            @Override
            public FragmentManager fragmentManager() {
                return MainActivity.this.getSupportFragmentManager();
            }
        };

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment, null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
