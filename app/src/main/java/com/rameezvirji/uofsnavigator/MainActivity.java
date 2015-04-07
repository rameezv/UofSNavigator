package com.rameezvirji.uofsnavigator;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public final static String SQUERY = "com.rameezvirji.uofsnavigator.searchquery";
    public final static String CURRX = "com.rameezvirji.uofsnavigator.currx";
    public final static String CURRY = "com.rameezvirji.uofsnavigator.curry";
    public final static String CURRF = "com.rameezvirji.uofsnavigator.currf";
    public int floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floor = 1;

        // Put the map into a variable and an integer to hold the floor
        final ImageView map = (ImageView) findViewById(R.id.mapview);

        // Map view
        final View main = findViewById(R.id.mapview);
        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView pin = (ImageView) findViewById(R.id.pinImage);
                pin.setVisibility(View.VISIBLE);
                pin.setTop(((int)event.getY()-150));
                pin.setLeft(((int)event.getX()-75));
                return true;

            }
        });

        // Button to move up a floor
        final Button btnUp = (Button) findViewById(R.id.btnUp);
        btnUp.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(0x33000000);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(0x00000000);
                    map.setImageResource(R.drawable.thorv2);
                    floor = 2;
                }
                return true;
            }
        });

        // Button to move down a floor
        final Button btnDown = (Button) findViewById(R.id.btnDown);
        btnDown.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(0x33000000);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(0x00000000);
                    map.setImageResource(R.drawable.thorv1);
                    floor = 1;
                }
                return true;
            }
        });

        // Search Button
        final ImageButton btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        btnSearch.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(0x33000000);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(0x00000000);
                    doSearch();
                }
                return true;
            }
        });
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
            Toast.makeText(getApplicationContext(), "There's nothing to set!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void doSearch() {
        Intent intent = new Intent(this, SearchResults.class);
        EditText searchQuery = (EditText) findViewById(R.id.txtSearch);
        String message = searchQuery.getText().toString();
        intent.putExtra(SQUERY, message);
        ImageView pin = (ImageView) findViewById(R.id.pinImage);
        intent.putExtra(CURRX, pin.getLeft()+150);
        intent.putExtra(CURRY, pin.getTop()+75);
        intent.putExtra(CURRF, floor);
        startActivity(intent);
    }
}
