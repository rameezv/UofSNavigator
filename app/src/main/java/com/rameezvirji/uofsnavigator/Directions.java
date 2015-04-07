package com.rameezvirji.uofsnavigator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Directions extends ActionBarActivity {
    int currNS, currEW, currF, destNS, destEW, destF;
    String destName;
    String[] directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        Bundle extras = getIntent().getExtras();
        currNS = extras.getInt("currNS");
        currEW = extras.getInt("currEW");
        currF = extras.getInt("currF");
        destNS = extras.getInt("destNS");
        destEW = extras.getInt("destEW");
        destF = extras.getInt("destF");
        destName = extras.getString("destName");

        //Toast.makeText(getApplicationContext(), ("NS(c,d): (" + currNS +"," + destNS +") EW(c,d): (" + currEW + "," + destEW + ")"), Toast.LENGTH_LONG).show();

        directions = new String[1];
        directions[0] = "Begin directions:";

        if (currF < destF) {
            addToDir("Go upstairs at the nearest stairway.");
        } else if (currF > destF) {
            addToDir("Go downstairs at the nearest stairway.");
        }

        if (currNS != destNS) {
            if (currNS < destNS) {
                addToDir("Head to the north side of the building.");
            } else if (currNS > destNS) {
                addToDir("Head to the south side of the building.");
            }
        }

        if (currEW != destEW) {
            if (currEW < destEW) {
                addToDir("Head to the east side of the building.");
            } else if (currEW > destEW)  {
                addToDir("Head to the west side of the building.");
            }
        }

        String lastDir = "Arrive at ";
        lastDir += destName + ".";
        addToDir(lastDir);

        populateDirections();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_directions, menu);
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

    public void addToDir(String dir) {
        String[] directionsBak = directions;
        directions = new String[directionsBak.length + 1];
        for (int i=0; i<directionsBak.length; i++) {
            directions[i] = directionsBak[i];
        }
        directions[directionsBak.length] = dir;
    }

    public void populateDirections() {
        ListView directionsList = (ListView) findViewById(R.id.directionList);

        ArrayList<String> direcs = new ArrayList<>();
        for (int i=1; i<directions.length; i++) {
            direcs.add(directions[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, direcs);

        directionsList.setAdapter(adapter);
    }
}
