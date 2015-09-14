package com.goka.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // All options in Configuration are optional. Use only those you really want to customize.
        /*
        ((WaveView) findViewById(R.id.wave))
                .paintWidth(3)
                .duringTime(3000)
                .maxRadius(100)
                .minRadius(40)
                .canTouch(true)
                .x(dm.widthPixels / 2)
                .y(dm.heightPixels / 2)
                .multiply(2.0f)
                .newTime(1000)
                .beginTransparent(128)
                .backgroundResource(R.drawable.abc_item_background_holo_dark)
                .refreshTime(50)
                .endTransparent(0)
                .transparentTransition(255)
                .transparentTransitionRadius(80)
                .start();
                */
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
