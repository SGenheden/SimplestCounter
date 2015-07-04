package com.genheden.simplestcounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int counter_val = getCounterValue();
        updateText(counter_val);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            updateText(0);
            updateCounterValue(0);
            return true;
        } else if (id == R.id.action_decrease) {
            int counter_val = getCounterValue();
            counter_val = counter_val - 1;
            updateText(counter_val);
            updateCounterValue(counter_val);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void increaseCounter(View view) {
        int counter_val = getCounterValue();
        counter_val = counter_val + 1;
        updateText(counter_val);
        updateCounterValue(counter_val);
    }

    private int getCounterValue() {
        // Access the preference for this Activity, retrieve the counter value
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getInt(getString(R.string.counter_value), 0);
    }

    private void updateText(int counter_value) {
        // Update the visible text
        TextView txt = (TextView) findViewById(R.id.counter_text);
        txt.setText(Integer.toString(counter_value));
    }

    private void updateCounterValue(int counter_value) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.counter_value), counter_value);
        editor.commit();
    }
}
