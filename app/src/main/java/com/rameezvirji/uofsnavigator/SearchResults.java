package com.rameezvirji.uofsnavigator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;


public class SearchResults extends ActionBarActivity {

    public String[] locations;
    Hashtable locHash = new Hashtable<String, LocationMap>();
    String query;
    float currX;
    float currY;
    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Bundle extras = getIntent().getExtras();
        query = extras.getString(MainActivity.SQUERY);
        currX = extras.getFloat(MainActivity.CURRX);
        currY = extras.getFloat(MainActivity.CURRY);
        resultList = (ListView) findViewById(R.id.searchResults);
        final TextView qLabel = (TextView) findViewById(R.id.sQueryDisplay);
        if (query.length() == 0) {
            qLabel.setText("All Results:");
        } else {
            qLabel.setText("Results for \"" + query + "\":");
        }
        createArray();
        populateList();

        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = resultList.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
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

       public void createArray() {
           locations = new String[1];
           locations[0] = "Bob's Burgers";
           locHash.put("Bob's Burgers", new LocationMap(0f,0f));
           addToArray("Tim Hortons", 0f, 0f);
           addToArray("Tim Robins Steak House", 0f, 0f);
           addToArray("John Cena's House of Pancakes", 0f, 0f);
           addToArray("McDonald's", 0f, 0f);
       }

        public void addToArray(String name, float xval, float yval) {
            String[] locationsBak = locations;
            locations = new String[locationsBak.length + 1];
            for (int i=0; i<locationsBak.length; i++) {
                locations[i] = locationsBak[i];
            }
            locations[locationsBak.length] = name;
            locHash.put(name, new LocationMap(xval, yval));
        }

        public void populateList() {
            ArrayList<String> results = new ArrayList<>();
            for (int i=0; i<locations.length; i++) {
                if (locations[i].toLowerCase().contains(query.toLowerCase())) {
                    results.add(locations[i]);
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
            resultList.setAdapter(adapter);
        }

}
